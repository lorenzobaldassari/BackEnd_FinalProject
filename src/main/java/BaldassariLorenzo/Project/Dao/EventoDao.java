package BaldassariLorenzo.Project.Dao;

import BaldassariLorenzo.Project.Entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EventoDao extends JpaRepository<Evento, UUID> {
}
