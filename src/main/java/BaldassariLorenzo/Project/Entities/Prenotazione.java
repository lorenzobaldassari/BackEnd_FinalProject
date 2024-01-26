package BaldassariLorenzo.Project.Entities;

import BaldassariLorenzo.Project.Dao.EventoDao;
import BaldassariLorenzo.Project.Dao.PrenotazioneDao;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
public class Prenotazione {

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

}
