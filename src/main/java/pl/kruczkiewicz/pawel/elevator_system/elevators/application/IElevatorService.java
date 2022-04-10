package pl.kruczkiewicz.pawel.elevator_system.elevators.application;

import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.model.*;

import java.util.UUID;

@Service
public interface IElevatorService {

    ElevatorDTO pickUp(Integer destinationFloorNumber, PickUpRequestDTO pickUpRequestDTO);
    
    ElevatorDTO patchElevatorStatus(UUID elevatorId, StateObjectDTO stateObjectDTO);

    ElevatorDTO patchElevatorDestinationFloor(UUID elevatorId, DestinationObjectDTO destinationObjectDTO);
}
