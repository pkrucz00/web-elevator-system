package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.enums.ElevatorStateEnum;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.DownState;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.IdleState;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.UpState;

import javax.persistence.MappedSuperclass;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Getter
@MappedSuperclass
@RequiredArgsConstructor
public abstract class ElevatorState {

    private final int stateNumber;

    public abstract ElevatorStateEnum getStateEnum();

    public abstract Integer computeDestinationFloor(Set<Integer> jobs);

    public static ElevatorState of(Integer givenStateNumber){
        return Arrays.stream(ElevatorStateEnum.values())
                .map(ElevatorStateEnum::getStateClass)
                .filter(elevatorState -> givenStateNumber.equals(elevatorState.stateNumber))
                .findAny().orElse(null);
    }

    public ElevatorState computeNextState(Integer currentFloor, Integer destinationFloor) {
        if (destinationFloor == null)
            return new IdleState();

        return destinationFloor - currentFloor > 0 ? new UpState() : new DownState();
    }
}
