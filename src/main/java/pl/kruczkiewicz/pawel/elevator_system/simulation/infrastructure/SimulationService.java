package pl.kruczkiewicz.pawel.elevator_system.simulation.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.dao.ElevatorRepository;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;
import pl.kruczkiewicz.pawel.elevator_system.simulation.domain.INextStepService;
import pl.kruczkiewicz.pawel.elevator_system.person.infrastructure.PersonRepository;
import pl.kruczkiewicz.pawel.elevator_system.simulation.application.ISimulationService;
import pl.kruczkiewicz.pawel.elevator_system.simulation.domain.BuildingState;
import pl.kruczkiewicz.pawel.model.BuildingStateDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulationService implements ISimulationService {

    private final INextStepService nextStepService;

    private final PersonRepository personRepository;
    private final ElevatorRepository elevatorRepository;

    private final SimulationMapper mapper;

    @Override
    public BuildingStateDTO getSimulationStatus() {
        List<ElevatorEntity> elevatorEntities = elevatorRepository.findAll();
        List<PersonEntity> waitingPeopleEntities = personRepository.findAllByElevatorPersonIsInIdIsNull();
        return mapper.elevatorsAndPeopleToBuildingState(
                BuildingState.of(elevatorEntities, waitingPeopleEntities));
    }

    @Override
    @Transactional
    public BuildingStateDTO performNextStep() {
        List<ElevatorEntity> elevators = nextStepService.performNextStep();

        elevatorRepository.saveAll(elevators);
        return getSimulationStatus();
    }
}
