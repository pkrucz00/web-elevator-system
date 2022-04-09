package pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.IElevatorService;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;
import pl.kruczkiewicz.pawel.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ElevatorService implements IElevatorService {
    private final List<PersonEntity> personEntities = new ArrayList<>();
    private final List<ElevatorEntity> elevatorEntities =
            Stream.iterate(1, i -> i + 1).map(ElevatorEntity::getInitialElevator)
                    .limit(4).toList();

    private final ElevatorMapper mapper;

    @Override
    public BuildingStateDTO pickUp(Integer currentFloorId, PickUpRequestDTO pickUpRequestDTO) {
        return null;
    }

    @Override
    public ElevatorDTO patchElevatorStatus(StatusObjectDTO statusObjectDTO) {

        return null;
    }

    @Override
    public ElevatorDTO patchElevatorDestinationFloor(DestinationObjectDTO destinationObjectDTO) {
        return null;
    }
}
