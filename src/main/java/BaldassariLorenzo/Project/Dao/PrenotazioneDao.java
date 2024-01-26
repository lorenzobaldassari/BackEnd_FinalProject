package BaldassariLorenzo.Project.Dao;

import BaldassariLorenzo.Project.Entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrenotazioneDao extends JpaRepository<Prenotazione, UUID> {
}
