package pl.kruczkiewicz.pawel.elevator_system.elevators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.IdleState;
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
@Table(name = "elevators")
@NoArgsConstructor
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

    @OneToMany(mappedBy = "elevatorPersonIsIn", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<PersonEntity> peopleInside;

    @OneToMany(mappedBy = "elevatorPersonAwaits",  fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonEntity> peopleWaiting;

    public Set<Integer> getJobs(){
        return Stream.concat(
                peopleInside.stream().map(PersonEntity::getDestinationFloor),
                peopleWaiting.stream().map(PersonEntity::getCurrentFloor))
                .collect(Collectors.toSet());
    }


    public ElevatorEntity addAwaitingPerson(PersonEntity personAwaiting) {
        if (peopleWaiting == null){
            peopleWaiting = new HashSet<>();
        }

        personAwaiting.setElevatorPersonAwaits(this);
        peopleWaiting.add(personAwaiting);

        return this;
    }

    public ElevatorEntity deleteAwaitingPerson(PersonEntity person){
        person.setElevatorPersonAwaits(null);
        peopleWaiting.remove(person);
        return this;
    }

    public ElevatorEntity addPersonInside(PersonEntity personInside){
        if (peopleWaiting == null){
            peopleWaiting = new HashSet<>();
        }

        personInside.setElevatorPersonIsIn(this);
        peopleInside.add(personInside);

        return this;
    }

    public ElevatorEntity deletePersonInside(PersonEntity person){
        person.setElevatorPersonIsIn(null);
        peopleInside.remove(person);
        return this;
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
}
