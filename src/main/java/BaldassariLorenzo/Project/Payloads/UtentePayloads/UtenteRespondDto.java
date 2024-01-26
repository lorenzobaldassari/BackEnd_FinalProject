package BaldassariLorenzo.Project.Payloads.UtentePayloads;

import java.util.UUID;

public record UtenteRespondDto(
        UUID id,
        String username
) {
}
