package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl;

import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;
import pl.kruczkiewicz.pawel.model.ElevatorStateDTO;

public class IdleState extends ElevatorState {

    public IdleState() {
        super(0);
    }

    @Override
    public ElevatorStateDTO getDtoEnum() {
        return ElevatorStateDTO.IDLE;
    }
}
