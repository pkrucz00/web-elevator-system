package pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;
import pl.kruczkiewicz.pawel.model.ElevatorDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ElevatorMapper {

    @Mapping(target = "numberOfPeopleInside", source = "peopleInside",
            qualifiedByName = "countPeople")
    ElevatorDTO elevatorEntityToElevatorDTO(ElevatorEntity entity);

    @Named("countPeople")
    default Integer countPeople(List<PersonEntity> people){
        return people.size();
    }
}
