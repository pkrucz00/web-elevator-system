package pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure;

import io.vavr.Function3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.elevators.application.IElevatorService;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.services.ElevatorManagementService;
import pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.dao.ElevatorRepository;
import pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.errors.ObjectNotFoundException;
import pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.mapper.ElevatorMapper;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;
import pl.kruczkiewicz.pawel.elevator_system.person.application.IPersonService;
import pl.kruczkiewicz.pawel.elevator_system.person.infrastructure.PersonRepository;
import pl.kruczkiewicz.pawel.model.*;

import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ElevatorService implements IElevatorService {
    private final ElevatorMapper elevatorMapper;
    private final ElevatorManagementService elevatorManagementService;

    private final ElevatorRepository elevatorRepository;

    private final IPersonService personService;

    @Override
    public BuildingStateDTO pickUp(Integer destinationFloorNumber, PickUpRequestDTO pickUpRequestDTO) {
        PersonEntity person = personService.addPersonToDatabase(destinationFloorNumber, pickUpRequestDTO);
        ElevatorEntity chosenElevator = elevatorManagementService.chooseRightElevator(pickUpRequestDTO);
//        chosenElevator.changeState();  //TODO wymyślić, na bazie czego winda powinna zmieniać stan
        return null;
    }

    @Override
    public ElevatorDTO patchElevatorStatus(UUID elevatorId, StateObjectDTO stateObjectDTO) {
        return patchWrapper(elevatorId, Function3.of(ElevatorMapper::patchState).apply(elevatorMapper, stateObjectDTO));
    }

    @Override
    public ElevatorDTO patchElevatorDestinationFloor(UUID elevatorId, DestinationObjectDTO destinationObjectDTO) {
        return patchWrapper(elevatorId, Function3.of(ElevatorMapper::patchDestination).apply(elevatorMapper, destinationObjectDTO));
    }

    private ElevatorDTO patchWrapper(UUID elevatorId, Function<ElevatorEntity, ElevatorEntity> patchingFunction){
        ElevatorEntity elevatorFromDatabase = elevatorRepository.findById(elevatorId)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Elevator with id %s not found", elevatorId)));
        ElevatorEntity patchedElevatorEntity = patchingFunction.apply(elevatorFromDatabase);
        ElevatorEntity savedElevatorEntity = elevatorRepository.save(patchedElevatorEntity);
        return elevatorMapper.elevatorEntityToElevatorDTO(savedElevatorEntity);
    }
}
