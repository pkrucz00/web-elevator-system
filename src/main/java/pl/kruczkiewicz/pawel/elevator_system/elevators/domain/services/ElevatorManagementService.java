package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.enums.ElevatorDirectionEnum;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.enums.ElevatorStateEnum;
import pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.dao.ElevatorRepository;
import pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.mapper.enums.ElevatorDirectionMapper;
import pl.kruczkiewicz.pawel.model.PickUpRequestDTO;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ElevatorManagementService implements IElevatorManagementService{

    private final ElevatorDirectionMapper directionMapper;

    private final ElevatorRepository elevatorRepository;


    public ElevatorEntity chooseRightElevator(PickUpRequestDTO pickUpRequestDTO) {
        Integer currentFloor = pickUpRequestDTO.getCurrentFloor();
        ElevatorStateEnum correspondingState = Optional.of(pickUpRequestDTO.getDirection())
                .map(directionMapper::directionDtoToEntity)
                .map(ElevatorDirectionEnum::getCorrespondingState)
                .orElseThrow();

        return chooseRightElevator(currentFloor, correspondingState);
    }

    private ElevatorEntity chooseRightElevator(Integer currentFloor, ElevatorStateEnum correspondingState){
        List<ElevatorEntity> elevators = elevatorRepository.findAll();

        Optional<ElevatorEntity> closestPassingByElevator = getClosestPassingByElevator(currentFloor, correspondingState, elevators);
        Optional<ElevatorEntity> closestIdleElevator = getClosestIdleElevator(currentFloor, elevators);
        ElevatorEntity randomElevator = getRandomElevator(elevators);

        return closestPassingByElevator.orElse(closestIdleElevator.orElse(randomElevator));
    }

    private Optional<ElevatorEntity> getClosestIdleElevator(Integer floorNumber, List<ElevatorEntity> elevators){
        return elevators.stream()
                .filter(elevator -> isElevatorOfGivenState(ElevatorStateEnum.IDLE, elevator))
                .min(
                    Comparator.comparing(elevator -> Math.abs(elevator.getCurrentFloor() - floorNumber)));
    }

    private Optional<ElevatorEntity> getClosestPassingByElevator(Integer floorNumber, ElevatorStateEnum correspondingState, List<ElevatorEntity> elevators) {
        return elevators.stream()
                .filter(elevator -> isElevatorOfGivenState(correspondingState, elevator))
                .filter(elevator -> elevator.isFloorInTrackRange(floorNumber))
                .min(Comparator.comparing(elevator -> Math.abs(elevator.getCurrentFloor() - floorNumber)));
    }

    private ElevatorEntity getRandomElevator(List<ElevatorEntity> elevators) {
        return elevators.get(new Random().nextInt(elevators.size()));
    }

    private boolean isElevatorOfGivenState(ElevatorStateEnum stateEnum, ElevatorEntity entity){
        return stateEnum.equals(entity.getState().getStateEnum());
    }
}
