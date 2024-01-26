package BaldassariLorenzo.Project.Payloads.EventoPayloads;

import java.time.LocalDate;
import java.util.UUID;

public record EventoRespondDto(
        UUID id,
        String titolo
) {
}
