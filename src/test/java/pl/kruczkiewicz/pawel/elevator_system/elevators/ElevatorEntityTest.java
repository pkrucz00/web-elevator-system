package pl.kruczkiewicz.pawel.elevator_system.elevators;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.ElevatorState;
import pl.kruczkiewicz.pawel.elevator_system.elevators.domain.state.impl.UpState;
import pl.kruczkiewicz.pawel.elevator_system.factories.ElevatorTestObjectFactory;
import pl.kruczkiewicz.pawel.elevator_system.factories.PersonTestObjectFactory;
import pl.kruczkiewicz.pawel.elevator_system.person.PersonEntity;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class ElevatorEntityTest {

    @Nested
    class GettingJobsTest{
        @Test
        void shouldGetNoJobs(){
            // given
            ElevatorEntity elevator = ElevatorTestObjectFactory.getEmptyElevator();

            // when
            Set<Integer> actualJobs = elevator.getJobs();

            // then
            assertThat(actualJobs).isEmpty();
        }

        @Test
        void shouldGetJobsFromWaiting() {
            // given
            Set<PersonEntity> peopleInside = Set.of();
            Set<PersonEntity> peopleWaiting = Stream.of(4,5,6)
                    .map(PersonTestObjectFactory::getPersonWithGivenCurrentFloor).collect(Collectors.toSet());
            ElevatorEntity elevator = ElevatorTestObjectFactory.getElevatorWithGivenPeopleSets(peopleInside, peopleWaiting);

            // when
            Set<Integer> actualJobs = elevator.getJobs();

            // then
            assertThat(actualJobs).isNotEmpty().contains(4,5,6);
        }

        @Test
        void shouldGetJobsFromWaitingAndInside() {
            // given
            Set<PersonEntity> peopleInside = Stream.of(1,2,3)
                    .map(PersonTestObjectFactory::getPersonWithGivenDestinationFloor).collect(Collectors.toSet());
            Set<PersonEntity> peopleWaiting = Stream.of(4,5,6)
                    .map(PersonTestObjectFactory::getPersonWithGivenCurrentFloor).collect(Collectors.toSet());
            ElevatorEntity elevator = ElevatorTestObjectFactory.getElevatorWithGivenPeopleSets(peopleInside, peopleWaiting);

            // when
            Set<Integer> actualJobs = elevator.getJobs();

            // then
            assertThat(actualJobs).isNotEmpty().contains(1,2,3,4,5,6);
         }
    }


    @Nested
    class IsFloorInTrackRangeTest{

        @Test
        void floorShouldBeInTrackRange() {
            floorInTrackTest(42, 40, 50, new UpState(), true);
        }

        @Test
        void floorShouldNotBeInTrackRange() {
            floorInTrackTest(43, 50, 60, new UpState(), false);
        }

        void floorInTrackTest(int floor, int currentFloor, int destinationFloor,
                              ElevatorState state, boolean expectedResponse){
            // given
            ElevatorEntity elevator = ElevatorTestObjectFactory
                    .getElevatorWithGivenFloorsAndState(
                            currentFloor, destinationFloor, state);

            // when
            boolean actualResponse = elevator.isFloorInTrackRange(floor);

            // then
            assertThat(actualResponse).isEqualTo(expectedResponse);
        }

    }

    @Test
    void shouldLetPeopleIn() {
        // given
        int currentElevatorFloor = 2;

        Set<PersonEntity> peopleWaitingBefore = getFewPeopleWaiting(currentElevatorFloor);
        Set<PersonEntity> peopleInsideBefore = getSetOfPeopleInTheElevator(currentElevatorFloor);
        ElevatorEntity elevator = ElevatorTestObjectFactory
                .getElevatorWithGivenPeopleSets(peopleInsideBefore, peopleWaitingBefore);
        elevator.setCurrentFloor(currentElevatorFloor);

        int expectedPeopleInsideSize = peopleInsideBefore.size() + 2;
        int expectedPeopleWaitingSize = peopleWaitingBefore.size() - 2;

        // when
        ElevatorEntity actualElevatorAfterLettingPeopleIn = elevator.letPeopleIn();

        // then
        assertThat(actualElevatorAfterLettingPeopleIn).isNotNull();
        assertThat(actualElevatorAfterLettingPeopleIn.getPeopleInside()).isNotNull().isNotEmpty()
                .hasSize(expectedPeopleInsideSize)
                .extracting("currentFloor")
                .containsOnly(currentElevatorFloor);
        assertThat(elevator.getPeopleWaiting()).hasSize(expectedPeopleWaitingSize)
                .extracting("currentFloor").doesNotContain(2);
    }

    private Set<PersonEntity> getFewPeopleWaiting(int currentElevatorFloor) {
        return Stream.of(1, currentElevatorFloor, currentElevatorFloor, 3, 4)
                .map(PersonTestObjectFactory::getPersonWithGivenCurrentFloor).collect(Collectors.toSet());
    }

    private Set<PersonEntity> getSetOfPeopleInTheElevator(int currentElevatorFloor) {
        return Stream.of(currentElevatorFloor, currentElevatorFloor, currentElevatorFloor)
                .map(PersonTestObjectFactory::getPersonWithGivenCurrentFloor).collect(Collectors.toSet());
    }

}