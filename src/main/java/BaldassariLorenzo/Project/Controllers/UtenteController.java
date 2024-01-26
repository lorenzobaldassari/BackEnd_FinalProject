package BaldassariLorenzo.Project.Controllers;

import BaldassariLorenzo.Project.Entities.Utente;
import BaldassariLorenzo.Project.Exceptions.BadRequestException;
import BaldassariLorenzo.Project.Payloads.UtentePayloads.UtenteRequestDto;
import BaldassariLorenzo.Project.Payloads.UtentePayloads.UtenteRequestForAdmin;
import BaldassariLorenzo.Project.Payloads.UtentePayloads.UtenteRespondDto;
import BaldassariLorenzo.Project.Services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;


    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public List<Utente> findAll(){
        return utenteService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Utente findById(@PathVariable UUID id) {
        return utenteService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        utenteService.delete(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.OK)
    public UtenteRespondDto modify(@RequestBody @Validated UtenteRequestForAdmin body,
                                   @PathVariable UUID id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            throw new BadRequestException("errore nel invio del payload per il metodo POST"+bindingResult.getAllErrors());
        } else {
            return utenteService.modifyByAdmin(body,id);
        }
    }

    @GetMapping("/me")
    public Utente getMyProfile(@AuthenticationPrincipal Utente currentUser) {
        // @AuthenticationPrincipal permette di accedere ai dati dell'utente attualmente autenticato
        // (perchè avevamo estratto l'id dal token e cercato l'utente nel db)
        return currentUser;
    }
    @DeleteMapping("/me")
    public void deleteMyself(@AuthenticationPrincipal Utente currentUser) {
        // @AuthenticationPrincipal permette di accedere ai dati dell'utente attualmente autenticato
        // (perchè avevamo estratto l'id dal token e cercato l'utente nel db)
        utenteService.delete(currentUser.getUuid());
    }
}
