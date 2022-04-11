package pl.kruczkiewicz.pawel.elevator_system.simulation.domain;

import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;

import java.util.List;

public interface INextStepService {

    List<ElevatorEntity> performNextStep();
}
