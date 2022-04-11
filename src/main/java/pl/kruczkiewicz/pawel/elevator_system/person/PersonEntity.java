package pl.kruczkiewicz.pawel.elevator_system.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;

@Getter
@Setter
@Entity
@Table(name = "people")
@NoArgsConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "current_floor")
    private Integer currentFloor;

    @Column(name = "destination_floor")
    private Integer destinationFloor;

    @Column(name = "elevator_inside_id")
    private UUID elevatorPersonIsInId;

    @Column(name = "elevator_waiting_id")
    private UUID elevatorPersonAwaitsId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(currentFloor, that.currentFloor) && Objects.equals(destinationFloor, that.destinationFloor) && Objects.equals(elevatorPersonIsInId, that.elevatorPersonIsInId) && Objects.equals(elevatorPersonAwaitsId, that.elevatorPersonAwaitsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currentFloor, destinationFloor, elevatorPersonIsInId, elevatorPersonAwaitsId);
    }

    public boolean isWaiting(){
        return isNull(elevatorPersonIsInId);
    }

    public boolean isPersonOnItsFloor(){
        return destinationFloor.equals(currentFloor);
    }

}
