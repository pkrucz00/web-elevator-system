package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.DownState;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.IdleState;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.UpState;
import pl.kruczkiewicz.pawel.model.ElevatorStateDTO;

import javax.persistence.MappedSuperclass;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Getter
@MappedSuperclass
@RequiredArgsConstructor
public abstract class ElevatorState {

    private final int stateNumber;

    private static final List<ElevatorState> inheritingClassesInstances = List.of(
            new UpState(), new IdleState(), new DownState());

    public abstract ElevatorStateDTO getDtoEnum();

    public static ElevatorState of(Integer givenStateNumber){
        return Arrays.stream(ElevatorStateEnum.values())
                .map(ElevatorStateEnum::getStateClass)
                .filter(elevatorState -> givenStateNumber.equals(elevatorState.stateNumber))
                .findAny().orElse(null);
    }
}
