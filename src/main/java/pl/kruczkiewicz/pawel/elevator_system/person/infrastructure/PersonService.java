package pl.kruczkiewicz.pawel.elevator_system.person.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;
import pl.kruczkiewicz.pawel.elevator_system.person.application.IPersonService;
import pl.kruczkiewicz.pawel.model.PickUpRequestDTO;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    @Override
    public PersonEntity addPersonToDatabase(Integer destinationFloor, PickUpRequestDTO pickUpRequest) {
        PersonEntity person = personMapper.destinationFloorAndPickupToPerson(destinationFloor, pickUpRequest);
        return personRepository.save(person);
    }
}
