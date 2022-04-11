package pl.kruczkiewicz.pawel.elevator_system.simulation.domain.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.dao.ElevatorRepository;
import pl.kruczkiewicz.pawel.elevator_system.simulation.domain.INextStepService;

import java.util.List;

@Service
@Profile("patch")
@RequiredArgsConstructor
public class PatchNextStepService implements INextStepService {

    private final ElevatorRepository elevatorRepository;

    @Override
    public List<ElevatorEntity> performNextStep() {
        return elevatorRepository.findAll().stream()
                .map(ElevatorEntity::letPeopleIn)
                .map(ElevatorEntity::letPeopleOut)
                .map(ElevatorEntity::moveAccordingToState)
                .toList();
    }
}
