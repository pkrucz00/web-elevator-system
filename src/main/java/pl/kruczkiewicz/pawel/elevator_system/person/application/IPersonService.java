package pl.kruczkiewicz.pawel.elevator_system.person.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;
import pl.kruczkiewicz.pawel.elevator_system.person.infrastructure.PersonRepository;
import pl.kruczkiewicz.pawel.model.PickUpRequestDTO;

@Service

public interface IPersonService {



    PersonEntity addPersonToDatabase(Integer currentFloor, PickUpRequestDTO pickUpRequest);
}
