package BaldassariLorenzo.Project.Entities;

import BaldassariLorenzo.Project.Dao.EventoDao;
import BaldassariLorenzo.Project.Dao.PrenotazioneDao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
public class Prenotazione {

    @Id
    @GeneratedValue
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

}
