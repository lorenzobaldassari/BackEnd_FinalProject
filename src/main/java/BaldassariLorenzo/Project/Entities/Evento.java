package BaldassariLorenzo.Project.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "eventi")
@Getter
@Setter
public class Evento {

    @Id
    @GeneratedValue
    private UUID uuid;
    private String titolo;
    private String descrizione;
    private String luogo;
    private LocalDate data;
    private int numero_di_posti_disponibili;
    private String urlImmagineDiProfilo;
    @JsonIgnore
    @OneToMany(mappedBy = "evento")
    private List<Prenotazione> listaDiPrenotazioni;

    @Override
    public String toString() {
        return "Evento{" +
                "uuid=" + uuid +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", luogo='" + luogo + '\'' +
                ", data=" + data +
                ", numero_di_posti_disponibili=" + numero_di_posti_disponibili +
                '}';
    }
}
