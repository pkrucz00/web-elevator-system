package pl.kruczkiewicz.pawel.elevator_system.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kruczkiewicz.pawel.elevator_system.elevators.ElevatorEntity;

import javax.persistence.*;
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

    @JoinColumn(name = "elevator_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ElevatorEntity elevator;

    public boolean isWaiting(){
        return isNull(elevator);
    }
}
