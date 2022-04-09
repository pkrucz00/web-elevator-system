package pl.kruczkiewicz.pawel.elevator_system.person.domain;

import io.vavr.Function2;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;

import java.util.List;

public record WaitingOnGivenFloor(int floorNumber, long numberOfPeople) {

    public static WaitingOnGivenFloor of(List<PersonEntity> people, int floorNumber){
        assert people.stream().allMatch(PersonEntity::isWaiting);
        int numberOfPeopleOnGivenFloor = people.stream()
                .filter(person -> floorNumber == person.getCurrentFloor())
                .mapToInt(e -> 1).sum();

        return new WaitingOnGivenFloor(floorNumber, numberOfPeopleOnGivenFloor);
    }

    public static List<WaitingOnGivenFloor> peopleToWaitingListEntity(List<PersonEntity> waiting){
        List<Integer> possibleFloors = waiting.stream().map(PersonEntity::getCurrentFloor).toList();
        return possibleFloors.stream().map(Function2.of(WaitingOnGivenFloor::of).apply(waiting)).toList();
    };
}
