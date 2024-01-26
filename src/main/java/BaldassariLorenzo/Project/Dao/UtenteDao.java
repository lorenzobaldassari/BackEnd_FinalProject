package BaldassariLorenzo.Project.Dao;

import BaldassariLorenzo.Project.Entities.Utente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtenteDao extends JpaRepository<Utente,UUID>{
    Optional<Utente> findByEmail(String email);
}
