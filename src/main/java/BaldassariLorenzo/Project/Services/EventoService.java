package BaldassariLorenzo.Project.Services;

import BaldassariLorenzo.Project.Dao.EventoDao;
import BaldassariLorenzo.Project.Dao.UtenteDao;
import BaldassariLorenzo.Project.Entities.Evento;
import BaldassariLorenzo.Project.Exceptions.ItemNotFoundException;
import BaldassariLorenzo.Project.Payloads.EventoPayloads.EventoRequestDto;
import BaldassariLorenzo.Project.Payloads.EventoPayloads.EventoRespondDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventoService {


    @Autowired
    private EventoDao eventoDao;

    public List<Evento> findAll(){
        return eventoDao.findAll();
    }

    public EventoRespondDto postEvento(EventoRequestDto body){
        Evento evento= new Evento();
        evento.setTitolo(body.Titolo());
        evento.setDescrizione(body.descrizione());
        evento.setData(body.data());
        evento.setLuogo(body.luogo());
        evento.setNumero_di_posti_disponibili(body.numero_di_posti_disponibili());
        evento.setUrlImmagineDiProfilo(body.urlImmagineDiProfilo());
        eventoDao.save(evento);
        return new EventoRespondDto(evento.getUuid(),evento.getTitolo());
    }
    public Evento findById(UUID id){
        return eventoDao.findById(id).orElseThrow(()->new ItemNotFoundException(id));
    }


    public void delete(UUID id){
        eventoDao.delete(this.findById(id));
    }

    public EventoRespondDto modify(UUID id, EventoRequestDto body){
        Evento evento=this.findById(id);
        evento.setTitolo(body.Titolo());
        evento.setDescrizione(body.descrizione());
        evento.setData(body.data());
        evento.setNumero_di_posti_disponibili(body.numero_di_posti_disponibili());
        evento.setUrlImmagineDiProfilo(body.urlImmagineDiProfilo());
        eventoDao.save(evento);
        return new EventoRespondDto(evento.getUuid(),evento.getTitolo());
    }
}
