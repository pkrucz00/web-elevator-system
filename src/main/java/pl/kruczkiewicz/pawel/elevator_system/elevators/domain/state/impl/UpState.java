package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl;

import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.enums.ElevatorStateEnum;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;

import java.util.Set;

public class UpState extends ElevatorState {
    public UpState() {
        super(1);
    }

    @Override
    public ElevatorStateEnum getStateEnum() {
        return ElevatorStateEnum.UP;
    }

    @Override
    public Integer computeDestinationFloor(Set<Integer> jobs) {
        return jobs.stream().reduce(Math::max).orElse(null);
    }
}
