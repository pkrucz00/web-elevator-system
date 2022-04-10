package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl;

import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.enums.ElevatorStateEnum;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;

import java.util.Set;

public class IdleState extends ElevatorState {

    public IdleState() {
        super(0);
    }

    @Override
    public ElevatorStateEnum getStateEnum() {
        return ElevatorStateEnum.IDLE;
    }

    @Override
    public Integer computeDestinationFloor(Set<Integer> jobs) {
        return null;
    }
}
