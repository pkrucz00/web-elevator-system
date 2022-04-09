package pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.errors;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
