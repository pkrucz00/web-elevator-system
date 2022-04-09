package pl.kruczkiewicz.pawel.elevator_system.simulation.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import pl.kruczkiewicz.pawel.api.SimulationApi;
import pl.kruczkiewicz.pawel.model.BuildingStateDTO;

@Controller
@RequiredArgsConstructor
public class SimulationController implements SimulationApi {

    private final ISimulationService service;

    @Override
    public ResponseEntity<BuildingStateDTO> getSimulationStatus() {
        return ResponseEntity.ok(service.getSimulationStatus());
    }

    @Override
    public ResponseEntity<BuildingStateDTO> nextStep() {
        return ResponseEntity.ok(service.performNextStep());
    }
}
