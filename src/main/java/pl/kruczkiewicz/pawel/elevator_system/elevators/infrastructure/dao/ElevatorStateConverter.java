package pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.dao;

import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ElevatorStateConverter implements AttributeConverter<ElevatorState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ElevatorState elevatorState) {
        return elevatorState.getStateNumber();
    }

    @Override
    public ElevatorState convertToEntityAttribute(Integer integer) {
        return ElevatorState.of(integer);
    }
}
