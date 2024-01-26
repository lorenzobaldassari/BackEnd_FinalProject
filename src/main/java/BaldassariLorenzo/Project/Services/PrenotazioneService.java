package BaldassariLorenzo.Project.Services;

import BaldassariLorenzo.Project.Dao.PrenotazioneDao;
import BaldassariLorenzo.Project.Entities.Prenotazione;
import BaldassariLorenzo.Project.Exceptions.ItemNotFoundException;
import BaldassariLorenzo.Project.Payloads.PrenotazionePayloads.PrenotazioneRequestDto;
import BaldassariLorenzo.Project.Payloads.PrenotazionePayloads.PrenotazioneRespondDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneDao prenotazioneDao;
    @Autowired
    UtenteService utenteService;
    @Autowired
    EventoService eventoService;

    public List<Prenotazione> findAll(){
        return prenotazioneDao.findAll();
    }

    public PrenotazioneRespondDto post(UUID uuid_evento,UUID uuid_utente){
        Prenotazione prenotazione=new Prenotazione();
        prenotazione.setUtente(utenteService.findById(uuid_utente));
        prenotazione.setEvento(eventoService.findById(uuid_evento));

        prenotazioneDao.save(prenotazione);
        return new PrenotazioneRespondDto(prenotazione.getUuid());
    }
    public Prenotazione findById(UUID uuid_prenotazione){
        return prenotazioneDao.findById(uuid_prenotazione).orElseThrow(()->new ItemNotFoundException(uuid_prenotazione));
    }
    public void delete(UUID id_prenotazione){
        prenotazioneDao.delete(this.findById(id_prenotazione));
    }
}
