package pl.kruczkiewicz.pawel.elevator_system.simulation.application.errors;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
