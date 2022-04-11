package pl.kruczkiewicz.pawel.elevator_system.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.IdleState;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ElevatorTestObjectFactory {

//    public ElevatorEntity getEntityWith

    public static ElevatorEntity getEmptyElevator() {
        return ElevatorEntity.builder()
                .number(1)
                .currentFloor(1)
                .destinationFloor(1)
                .state(new IdleState())
                .peopleInside(new HashSet<>())
                .peopleWaiting(new HashSet<>())
                .build();
    }

    public static ElevatorEntity getElevatorWithGivenPeopleSets(
            Set<PersonEntity> peopleInside,
            Set<PersonEntity> peopleWaiting){
        return ElevatorEntity.builder()
                .peopleInside(peopleInside)
                .peopleWaiting(peopleWaiting)
                .build();
    }

    public static ElevatorEntity getElevatorWithGivenFloorsAndState(
            int currentFloor, int destinationFloor, ElevatorState state){
        return ElevatorEntity.builder()
                .currentFloor(currentFloor)
                .destinationFloor(destinationFloor)
                .state(state).build();
    }


}
