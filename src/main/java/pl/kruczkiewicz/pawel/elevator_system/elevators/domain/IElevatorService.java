package pl.kruczkiewicz.pawel.elevator_system.elevators.domain;

import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.model.*;

@Service
public interface IElevatorService {

    BuildingStateDTO pickUp(Integer currentFloorId, PickUpRequestDTO pickUpRequestDTO);
    
    ElevatorDTO patchElevatorStatus(StatusObjectDTO statusObjectDTO);

    ElevatorDTO patchElevatorDestinationFloor(DestinationObjectDTO destinationObjectDTO);
}
