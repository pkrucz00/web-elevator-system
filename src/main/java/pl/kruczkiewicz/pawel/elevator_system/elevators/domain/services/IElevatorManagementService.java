package pl.kruczkiewicz.pawel.elevator_system.elevators.domain.services;

import org.springframework.stereotype.Service;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;
import pl.kruczkiewicz.pawel.model.PickUpRequestDTO;

@Service
public interface IElevatorManagementService {

    ElevatorEntity chooseRightElevator(PickUpRequestDTO pickUpRequestDTO);


}
