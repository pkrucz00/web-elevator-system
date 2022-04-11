package pl.kruczkiewicz.pawel.elevator_system.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonTestObjectFactory {

    public static PersonEntity getExemplarPerson(){
        return PersonEntity.builder()
                .id(UUID.randomUUID())
                .currentFloor(1)
                .destinationFloor(1)
                .build();
    }

    public static PersonEntity getPersonWithGivenCurrentFloor(int currentFloor){
        return getExemplarPerson().toBuilder()
                .currentFloor(currentFloor)
                .build();
    }

    public static PersonEntity getPersonWithGivenDestinationFloor(int destinationFloor){
        return getExemplarPerson().toBuilder()
                .destinationFloor(destinationFloor)
                .build();
    }
}
