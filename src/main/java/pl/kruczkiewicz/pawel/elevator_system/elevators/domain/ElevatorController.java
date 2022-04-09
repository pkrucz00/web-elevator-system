package pl.kruczkiewicz.pawel.elevator_system.elevators.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import pl.kruczkiewicz.pawel.api.ElevatorsApi;
import pl.kruczkiewicz.pawel.model.*;

@Controller
public class ElevatorController implements ElevatorsApi {

    private final IElevatorService elevatorService;

    @Autowired
    public ElevatorController(IElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @Override
    public ResponseEntity<ElevatorDTO> patchElevatorDestinationFloor(DestinationObjectDTO destinationObjectDTO) {
        return ResponseEntity.ok(elevatorService.patchElevatorDestinationFloor(destinationObjectDTO));
    }

    @Override
    public ResponseEntity<ElevatorDTO> patchElevatorStatus(StatusObjectDTO statusObjectDTO) {
        return ResponseEntity.ok(elevatorService.patchElevatorStatus(statusObjectDTO));
    }

    @Override
    public ResponseEntity<BuildingStateDTO> pickUp(Integer currentFloorId, PickUpRequestDTO pickUpRequestDTO) {
        return ResponseEntity.ok(elevatorService.pickUp(currentFloorId, pickUpRequestDTO));
    }
}
