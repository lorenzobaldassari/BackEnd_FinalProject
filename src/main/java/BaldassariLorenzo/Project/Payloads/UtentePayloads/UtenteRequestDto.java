package BaldassariLorenzo.Project.Payloads.UtentePayloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UtenteRequestDto(
        @Size(min=3,message="username  troppo corto")
        @NotNull(message="il campo non deve essere null")
        String username,
        @Email
        String email,
        @Size(min=3,message="username  troppo corto")
        @NotNull(message="il campo non deve essere null")
        String password

) {
}
