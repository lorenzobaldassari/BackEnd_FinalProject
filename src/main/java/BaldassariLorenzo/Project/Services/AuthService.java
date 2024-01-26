package BaldassariLorenzo.Project.Services;

import BaldassariLorenzo.Project.Entities.Utente;
import BaldassariLorenzo.Project.Payloads.AuthPayloads.AuthRequestDTO;
import BaldassariLorenzo.Project.Security.JWTTtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTtools jwtTtools;

    public String authenticateUser(AuthRequestDTO body){
        Utente utente=  utenteService.findByEmail(body.email());
        return jwtTtools.createToken(utente);

    }
}
