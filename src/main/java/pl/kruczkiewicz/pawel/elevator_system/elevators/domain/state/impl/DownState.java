package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl;

import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.enums.ElevatorStateEnum;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;

import java.util.Set;

public class DownState extends ElevatorState {
    public DownState() {
        super(-1);
    }

    @Override
    public ElevatorStateEnum getStateEnum() {
        return ElevatorStateEnum.DOWN;
    }

    @Override
    public Integer computeDestinationFloor(Set<Integer> jobs) {
        return jobs.stream().reduce(Math::min).orElse(null);
    }
}
