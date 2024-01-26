package BaldassariLorenzo.Project.Payloads.PrenotazionePayloads;

import BaldassariLorenzo.Project.Entities.Evento;
import BaldassariLorenzo.Project.Entities.Utente;

public record PrenotazioneRequestDto(

        Utente utente,
        Evento evento
) {
}
