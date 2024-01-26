package BaldassariLorenzo.Project.Payloads.EventoPayloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventoRequestDto(
        @Size(min=3,message="username  troppo corto")
        @NotNull(message="il campo non deve essere null")
        String Titolo,

        @Size(min=3,message="username  troppo corto")
        @NotNull(message="il campo non deve essere null")
        String descrizione,
        @Size(min=3,message="username  troppo corto")
        @NotNull(message="il campo non deve essere null")
        String luogo,
        LocalDate data,

        @NotNull(message="il campo non deve essere null")
        int numero_di_posti_disponibili,

        @NotNull(message="il campo non deve essere null")
        String urlImmagineDiProfilo
) {
}
