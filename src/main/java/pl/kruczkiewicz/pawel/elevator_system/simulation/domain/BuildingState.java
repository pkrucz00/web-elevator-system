package pl.kruczkiewicz.pawel.elevator_system.simulation.domain;

import io.vavr.Function2;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;
import pl.kruczkiewicz.pawel.elevator_system.person.domain.WaitingOnGivenFloor;

import java.util.List;

public record BuildingState(List<ElevatorEntity> elevators, List<WaitingOnGivenFloor> waitingPeople) {

    public static BuildingState of(List<ElevatorEntity> elevator, List<PersonEntity> waitingPeople){
        List<WaitingOnGivenFloor> waitingOnGivenFloors = WaitingOnGivenFloor.peopleToWaitingListEntity(waitingPeople);
        return new BuildingState(elevator, waitingOnGivenFloors);
    }


}
