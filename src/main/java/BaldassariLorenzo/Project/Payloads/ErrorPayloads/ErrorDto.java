package BaldassariLorenzo.Project.Payloads.ErrorPayloads;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ErrorDto(
        String message,
        LocalDateTime data
) {
}
