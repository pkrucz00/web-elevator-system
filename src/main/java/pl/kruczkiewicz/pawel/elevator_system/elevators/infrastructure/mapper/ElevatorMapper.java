package pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;
import pl.kruczkiewicz.pawel.model.DestinationObjectDTO;
import pl.kruczkiewicz.pawel.model.ElevatorDTO;
import pl.kruczkiewicz.pawel.model.StateObjectDTO;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = {ElevatorStateMapper.class})
public interface ElevatorMapper {

    @Mapping(target = "numberOfPeopleInside", source = "peopleInside",
            qualifiedByName = "countPeople")
    @Mapping(target = "state", source = "state", qualifiedByName = "stateToDTO")
    ElevatorDTO elevatorEntityToElevatorDTO(ElevatorEntity entity);

    @Mapping(target = "state", source = "state", qualifiedByName = "stateToEntity")
    ElevatorEntity patchState(StateObjectDTO stateObjectDTO, @MappingTarget ElevatorEntity elevatorFromDatabase);

    ElevatorEntity patchDestination(DestinationObjectDTO destinationObjectDTO, @MappingTarget ElevatorEntity elevatorFromDatabase);


    @Named("countPeople")
    default Integer countPeople(Collection<PersonEntity> people){
        return people.size();
    }
}
