package pl.kruczkiewicz.pawel.elevator_system.person.infrastructure;

import org.mapstruct.Mapper;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;
import pl.kruczkiewicz.pawel.elevator_system.person.domain.WaitingOnGivenFloor;
import pl.kruczkiewicz.pawel.model.PickUpRequestDTO;
import pl.kruczkiewicz.pawel.model.WaitingOnGivenFloorDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonEntity destinationFloorAndPickupToPerson(Integer destinationFloor, PickUpRequestDTO pickUpRequest);

    List<WaitingOnGivenFloorDTO> waitingListEntityToDTO(List<WaitingOnGivenFloor> waiting);
}
