package pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;

import java.util.UUID;

@Repository
public interface ElevatorRepository extends JpaRepository<ElevatorEntity, UUID> {
}
