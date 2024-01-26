package BaldassariLorenzo.Project.Services;

import BaldassariLorenzo.Project.Dao.UtenteDao;
import BaldassariLorenzo.Project.Entities.Utente;
import BaldassariLorenzo.Project.Exceptions.EmailAlreadyInDbException;
import BaldassariLorenzo.Project.Exceptions.UnauthorizedException;
import BaldassariLorenzo.Project.Payloads.AuthPayloads.AuthRequestDTO;
import BaldassariLorenzo.Project.Payloads.UtentePayloads.UtenteRequestDto;
import BaldassariLorenzo.Project.Payloads.UtentePayloads.UtenteRespondDto;
import BaldassariLorenzo.Project.Security.JWTTtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTtools jwtTtools;
    @Autowired
    private UtenteDao utenteDao;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUser(AuthRequestDTO body){
        Utente utente=  utenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), utente.getPassword())) {
            return jwtTtools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }

    }
    public UtenteRespondDto post(UtenteRequestDto body){
        Optional<Utente> checkEmail= utenteDao.findByEmail(body.email());
        System.err.println(checkEmail);
        if(checkEmail.isEmpty()){
        Utente utente= new Utente();
        utente.setEmail(body.email());
        utente.setPassword(bcrypt.encode(body.password()));
        utente.setUsername((body.username()));
        utente.setRole("UTENTE_SEMPLICE");
        utenteDao.save(utente);
        return  new UtenteRespondDto(utente.getUuid(), utente.getUsername());
        } else{
            throw new EmailAlreadyInDbException(body.email());
        }
    }
}
