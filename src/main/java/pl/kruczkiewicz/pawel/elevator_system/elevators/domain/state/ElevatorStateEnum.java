package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state;

import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.DownState;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.IdleState;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.UpState;

public enum ElevatorStateEnum {
    UP, DOWN, IDLE;

    public static ElevatorState getStateClass(ElevatorStateEnum stateEnum){
        return switch (stateEnum){
            case UP -> new UpState();
            case DOWN -> new DownState();
            case IDLE -> new IdleState();
        };
    }
}
