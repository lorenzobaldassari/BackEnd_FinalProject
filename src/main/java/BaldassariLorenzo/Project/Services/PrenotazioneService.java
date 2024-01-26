package BaldassariLorenzo.Project.Services;

import BaldassariLorenzo.Project.Dao.PrenotazioneDao;
import BaldassariLorenzo.Project.Entities.Prenotazione;
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

    private List<Prenotazione> findAll(){
        return prenotazioneDao.findAll();
    }

//    private PrenotazioneRespondDto post(PrenotazioneRequestDto body, UUID uuid_evento){
//        Prenotazione prenotazione=new Prenotazione();
//        prenotazione.setUtente();
//    }
}
