package pl.kruczkiewicz.pawel.elevator_system.elevators.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import pl.kruczkiewicz.pawel.api.ElevatorsApi;
import pl.kruczkiewicz.pawel.model.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ElevatorController implements ElevatorsApi {
    private final IElevatorService elevatorService;

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
