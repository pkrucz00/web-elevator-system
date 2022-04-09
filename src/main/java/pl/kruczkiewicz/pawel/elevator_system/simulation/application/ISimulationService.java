package pl.kruczkiewicz.pawel.elevator_system.simulation.application;

import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.model.BuildingStateDTO;

@Service
public interface ISimulationService {

    BuildingStateDTO getSimulationStatus();

    BuildingStateDTO performNextStep();
}
