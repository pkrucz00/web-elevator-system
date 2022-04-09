package pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorStateEnum;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;
import pl.kruczkiewicz.pawel.model.DestinationObjectDTO;
import pl.kruczkiewicz.pawel.model.ElevatorDTO;
import pl.kruczkiewicz.pawel.model.ElevatorStateDTO;
import pl.kruczkiewicz.pawel.model.StateObjectDTO;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ElevatorMapper {

    @Mapping(target = "numberOfPeopleInside", source = "peopleInside",
            qualifiedByName = "countPeople")

    @Mapping(target = "state", source = "state", qualifiedByName = "stateToDTO")
    ElevatorDTO elevatorEntityToElevatorDTO(ElevatorEntity entity);

    @Mapping(target = "state", source = "state", qualifiedByName = "stateToEntity")
    ElevatorEntity patchState(StateObjectDTO stateObjectDTO, @MappingTarget ElevatorEntity elevatorFromDatabase);

    ElevatorEntity patchDestination(DestinationObjectDTO destinationObjectDTO, @MappingTarget ElevatorEntity elevatorFromDatabase);

    ElevatorStateEnum enumDtoToEntity(ElevatorStateDTO stateDTO);

    @Named("countPeople")
    default Integer countPeople(Collection<PersonEntity> people){
        return people.size();
    }

    @Named("stateToDTO")
    default ElevatorStateDTO getElevatorStateDTO(ElevatorState elevatorState){
        return elevatorState.getDtoEnum();
    }

    @Named("stateToEntity")
    default ElevatorState getElevatorState(ElevatorStateDTO elevatorStateDTO){
        return ElevatorStateEnum.getStateClass(enumDtoToEntity(elevatorStateDTO));
    }
}
