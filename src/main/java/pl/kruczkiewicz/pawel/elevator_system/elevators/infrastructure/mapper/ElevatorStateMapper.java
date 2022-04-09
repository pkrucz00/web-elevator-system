package pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorStateEnum;
import pl.kruczkiewicz.pawel.model.ElevatorStateDTO;

@Mapper(componentModel = "spring")
public interface ElevatorStateMapper {

    ElevatorStateEnum enumDtoToEntity(ElevatorStateDTO stateDTO);

    @Named("stateToDTO")
    default ElevatorStateDTO getElevatorStateDTO(ElevatorState elevatorState){
        return elevatorState.getDtoEnum();
    }

    @Named("stateToEntity")
    default ElevatorState getElevatorState(ElevatorStateDTO elevatorStateDTO){
        return ElevatorStateEnum.getStateClass(enumDtoToEntity(elevatorStateDTO));
    }
}
