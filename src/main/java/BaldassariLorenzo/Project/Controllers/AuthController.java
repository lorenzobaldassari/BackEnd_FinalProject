package BaldassariLorenzo.Project.Controllers;

import BaldassariLorenzo.Project.Entities.Utente;
import BaldassariLorenzo.Project.Exceptions.BadRequestException;
import BaldassariLorenzo.Project.Exceptions.UnauthorizedException;
import BaldassariLorenzo.Project.Payloads.AuthPayloads.AuthRequestDTO;
import BaldassariLorenzo.Project.Payloads.UtentePayloads.UtenteRequestDto;
import BaldassariLorenzo.Project.Payloads.UtentePayloads.UtenteRespondDto;
import BaldassariLorenzo.Project.Services.AuthService;
import BaldassariLorenzo.Project.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteRespondDto register(@RequestBody @Validated UtenteRequestDto body,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            throw new BadRequestException("errore nel invio del payload per il metodo POST"+bindingResult.getAllErrors());
        } else {

            return authService.post(body);
        }
    }
    @PostMapping("/login")
    public String getToken(@RequestBody AuthRequestDTO body){
        String accessToken= authService.authenticateUser(body);
        return accessToken;

    }
}
