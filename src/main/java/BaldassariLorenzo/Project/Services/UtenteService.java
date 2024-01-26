package BaldassariLorenzo.Project.Services;

import BaldassariLorenzo.Project.Dao.UtenteDao;
import BaldassariLorenzo.Project.Entities.Utente;
import BaldassariLorenzo.Project.Exceptions.ItemNotFoundException;
import BaldassariLorenzo.Project.Payloads.UtentePayloads.UtenteRequestDto;
import BaldassariLorenzo.Project.Payloads.UtentePayloads.UtenteRequestForAdmin;
import BaldassariLorenzo.Project.Payloads.UtentePayloads.UtenteRespondDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    private UtenteDao utenteDao;

    public List<Utente> findAll(){
        return utenteDao.findAll();
    }


    public Utente findById(UUID id){
       return utenteDao.findById(id).orElseThrow(()->new ItemNotFoundException(id));
    }

    public void delete(UUID id){
        utenteDao.delete(this.findById(id));
    }
    public UtenteRespondDto modifyByAdmin(UtenteRequestForAdmin body, UUID id){
        Utente pwUser=this.findById(id);
        Utente utente= new Utente();
        utente.setEmail(body.email());
        utente.setPassword(pwUser.getPassword());
        utente.setUsername(body.username());
        utente.setRole(body.role());
        utenteDao.save(utente);
        return  new UtenteRespondDto(utente.getUuid(), utente.getUsername());
    }
    public UtenteRespondDto modifyByUtente(UtenteRequestDto body, UUID id){
        Utente pwUser=this.findById(id);
        Utente utente= new Utente();
        utente.setEmail(body.email());
        utente.setPassword(pwUser.getPassword());
        utente.setUsername(body.username());
        utente.setRole("UTENTE_SEMPLICE");
        utenteDao.save(utente);
        return  new UtenteRespondDto(utente.getUuid(), utente.getUsername());
    }

    public Utente findByEmail(String email){
        return utenteDao.findByEmail(email).orElseThrow(()->new ItemNotFoundException(email));
    }
}
