package pl.kruczkiewicz.pawel.elevator_system.elevators;

import lombok.*;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@Table(name = "elevators")
@NoArgsConstructor
@AllArgsConstructor
public class ElevatorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "current_floor")
    private Integer currentFloor;

    @Column(name = "destination_floor")
    private Integer destinationFloor;

    @Column(name = "state")
    private ElevatorState state;

    @OneToMany(mappedBy = "elevatorPersonIsInId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonEntity> peopleInside;

    @OneToMany(mappedBy = "elevatorPersonAwaitsId",  fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<PersonEntity> peopleWaiting;

    public Set<Integer> getJobs(){
        return Stream.concat(
                peopleInside.stream().map(PersonEntity::getDestinationFloor),
                peopleWaiting.stream().map(PersonEntity::getCurrentFloor))
                .collect(Collectors.toSet());
    }


    public ElevatorEntity addAwaitingPerson(PersonEntity personAwaiting) {
        if (peopleWaiting == null){
            peopleWaiting = new HashSet<>();    //todo change it to TreeSet and add comparator
        }

        personAwaiting.setElevatorPersonAwaitsId(this.id);
        peopleWaiting.add(personAwaiting);

        return this;
    }

    public PersonEntity addPersonInside(PersonEntity personInside){
        if (peopleInside == null){
            peopleInside = new HashSet<>();
        }

        personInside.setElevatorPersonIsInId(this.id);
        peopleInside.add(personInside);
        return personInside;
    }


    public ElevatorEntity changeDestinationFloorBasedOnJobs(){
        Integer newDestinationFloor = state.computeDestinationFloor(getJobs());
        this.setDestinationFloor(newDestinationFloor);

        return this;
    }

    public boolean isFloorInTrackRange(int floor){
        return switch (state.getStateEnum()){
            case IDLE -> false;
            case UP -> currentFloor <= floor && floor <= destinationFloor;
            case DOWN -> destinationFloor <= floor && floor <= currentFloor;
        };
    }

    public ElevatorEntity changeStateBasedOnDestination() {
        setState(state.computeNextState(currentFloor, destinationFloor));
        return this;
    }

    public ElevatorEntity moveAccordingToState() {
        int floorChange = state.getStateNumber();
        int newCurrentFloor = currentFloor + floorChange;
        setCurrentFloor(newCurrentFloor);
        peopleInside.forEach(person -> person.setCurrentFloor(newCurrentFloor));

        return this;
    }

    public ElevatorEntity letPeopleIn() {
        List<PersonEntity> newPeopleInside = peopleWaiting.stream()
                .filter(person -> currentFloor.equals(person.getCurrentFloor()))
                .map(this::addPersonInside).toList();
        newPeopleInside.forEach(person -> person.setElevatorPersonAwaitsId(null));
        peopleWaiting.removeAll(newPeopleInside);
        return this;
    }

    public ElevatorEntity letPeopleOut() {
        List<PersonEntity> peopleThatCanExit = peopleInside.stream().filter(PersonEntity::isPersonOnItsFloor).toList();
        peopleThatCanExit.forEach(person -> person.setElevatorPersonIsInId(null));
        peopleInside.removeAll(peopleThatCanExit);
        return this;
    }
}
