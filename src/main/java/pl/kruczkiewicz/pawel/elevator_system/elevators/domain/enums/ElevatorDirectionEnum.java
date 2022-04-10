package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.enums;

public enum ElevatorDirectionEnum {
    UP, DOWN;

    public static ElevatorStateEnum getCorrespondingState(ElevatorDirectionEnum directionEnum){
        return switch (directionEnum){
            case UP -> ElevatorStateEnum.UP;
            case DOWN -> ElevatorStateEnum.DOWN;
        };
    }
}
