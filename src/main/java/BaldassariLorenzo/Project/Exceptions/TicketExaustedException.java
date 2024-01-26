package BaldassariLorenzo.Project.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class TicketExaustedException extends RuntimeException {
    public TicketExaustedException() {
        super("posti esauriti per questo evento mi dispiace!");
    }
}
