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

    public ElevatorEntity changeDestinationFloorBasedOnJobs(){
        Integer newDestinationFloor = state.computeDestinationFloor(getJobs());
        this.setDestinationFloor(newDestinationFloor);

        return this;
    }

    public boolean isFloorInTrackRange(int floor){
        return switch (state.getStateEnum()){
            case UP -> currentFloor <= floor && floor <= destinationFloor;
            case DOWN -> destinationFloor <= floor && floor <= currentFloor;
            case IDLE -> false;
        };
    }

    public static ElevatorEntity getInitialElevator(int number){
        List<UUID> uuidList = Stream.of(
                "6816aa02-c8a3-41ea-be6b-7ec5b363bf3c",
                "a3e467b0-2a19-48c6-bf22-4d2479001a16",
                "606b4c8e-df55-40fb-b97f-fbbe80045ee7",
                "60a88596-bbdc-4905-985d-563e07ab14c7",
                "62be4663-7ff5-44f6-a401-a183411443aa").map(UUID::fromString).toList();

        var elevator = new ElevatorEntity();
        elevator.setId(uuidList.get(number-1));
        elevator.setNumber(number);
        elevator.setCurrentFloor(0);
        elevator.setState(new IdleState());
        return elevator;
    }
}
