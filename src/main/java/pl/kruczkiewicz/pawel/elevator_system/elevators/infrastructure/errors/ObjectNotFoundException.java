package pl.kruczkiewicz.pawel.elevator_system.elevators.infrastructure.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ObjectNotFoundException extends ResponseStatusException {
    public ObjectNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
