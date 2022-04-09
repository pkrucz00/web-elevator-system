package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl;

import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;
import pl.kruczkiewicz.pawel.model.ElevatorStateDTO;

public class DownState extends ElevatorState {
    public DownState() {
        super(-1);
    }

    @Override
    public ElevatorStateDTO getDtoEnum() {
        return ElevatorStateDTO.DOWN;
    }
}
