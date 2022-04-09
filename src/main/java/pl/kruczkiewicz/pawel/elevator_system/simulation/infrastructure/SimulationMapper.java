package pl.kruczkiewicz.pawel.elevator_system.simulation.infrastructure;

import org.mapstruct.Mapper;
import pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.mapper.ElevatorMapper;
import pl.kruczkiewicz.pawel.elevator_system.person.infrastructure.PersonMapper;
import pl.kruczkiewicz.pawel.elevator_system.simulation.domain.BuildingState;
import pl.kruczkiewicz.pawel.model.BuildingStateDTO;

@Mapper(componentModel = "spring", uses = {PersonMapper.class, ElevatorMapper.class})
public interface SimulationMapper {

    BuildingStateDTO elevatorsAndPeopleToBuildingState(BuildingState buildingState);
}
