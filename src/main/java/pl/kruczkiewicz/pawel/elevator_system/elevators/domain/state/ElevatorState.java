package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.enums.ElevatorStateEnum;

import javax.persistence.MappedSuperclass;
import java.util.Arrays;
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
}
