package pl.kruczkiewicz.pawel.elevator_system.person.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {

    // people waiting for an elevator
    List<PersonEntity> findAllByElevatorPersonIsInIsNull();

    // people in an elevator
    List<PersonEntity> findAllByElevatorPersonIsInIsNotNull();
}
