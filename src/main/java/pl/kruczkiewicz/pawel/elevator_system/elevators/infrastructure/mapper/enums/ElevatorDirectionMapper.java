package pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.mapper.enums;

import org.mapstruct.Mapper;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.enums.ElevatorDirectionEnum;
import pl.kruczkiewicz.pawel.model.DirectionDTO;

@Mapper(componentModel = "spring")
public interface ElevatorDirectionMapper {

    ElevatorDirectionEnum directionDtoToEntity(DirectionDTO dto);
}
