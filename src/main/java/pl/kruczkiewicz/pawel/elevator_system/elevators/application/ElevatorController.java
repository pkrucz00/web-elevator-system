package pl.kruczkiewicz.pawel.elevator_system.elevators.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import pl.kruczkiewicz.pawel.api.ElevatorsApi;
import pl.kruczkiewicz.pawel.model.*;

import java.util.UUID;

@Controller
public class ElevatorController implements ElevatorsApi {
    private final IElevatorService elevatorService;

    @Autowired
    public ElevatorController(IElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @Override
    public ResponseEntity<ElevatorDTO> patchElevatorDestinationFloor(UUID elevatorId, DestinationObjectDTO destinationObjectDTO) {
        return ResponseEntity.ok(elevatorService.patchElevatorDestinationFloor(elevatorId, destinationObjectDTO));
    }

    @Override
    public ResponseEntity<ElevatorDTO> patchElevatorState(UUID elevatorId, StateObjectDTO stateObjectDTO) {
        return ResponseEntity.ok(elevatorService.patchElevatorStatus(elevatorId, stateObjectDTO));
    }

    @Override
    public ResponseEntity<BuildingStateDTO> pickUp(Integer currentFloorId, PickUpRequestDTO pickUpRequestDTO) {
        return ResponseEntity.ok(elevatorService.pickUp(currentFloorId, pickUpRequestDTO));
    }
}
