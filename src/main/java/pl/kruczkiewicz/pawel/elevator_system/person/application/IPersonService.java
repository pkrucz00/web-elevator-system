package pl.kruczkiewicz.pawel.elevator_system.person.application;

import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;

import java.util.List;

@Service
public interface IPersonService {

    List<PersonEntity> movePeopleIntoElevators(List<ElevatorEntity> elevators);

    void movePeopleOutOfElevators();
}
