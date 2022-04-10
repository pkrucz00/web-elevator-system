package pl.kruczkiewicz.pawel.elevator_system.person.infrastructure;

import io.vavr.Function2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;
import pl.kruczkiewicz.pawel.elevator_system.person.application.IPersonService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {

    private final PersonMapper personMapper;

    private final PersonRepository personRepository;


    @Override
    public List<PersonEntity> movePeopleIntoElevators(List<ElevatorEntity> elevators) {
        List<PersonEntity> peopleWaiting = personRepository.findAllByElevatorPersonIsInIsNull();
        return peopleWaiting.stream()
                .map(Function2.of(this::enterElevatorIfPossible).apply(elevators))
                .toList();
    }

    private PersonEntity enterElevatorIfPossible(List<ElevatorEntity> elevators, PersonEntity person) {
        Optional<ElevatorEntity> elevatorOnPersonFloor =
                elevators.stream()
                        .filter(elevator -> person.getCurrentFloor().equals(elevator.getCurrentFloor()))
                        .findAny();
        elevatorOnPersonFloor.ifPresent(person::enterElevator);
        return person;
    }

    @Override
    public void movePeopleOutOfElevators() {
        List<PersonEntity> peopleInElevators = personRepository.findAllByElevatorPersonIsInIsNotNull();
        peopleInElevators.stream()
                .filter(PersonEntity::isPersonOnItsFloor)
                .map(PersonEntity::exitElevator);
//                .forEach(personRepository::delete);
    }
}
