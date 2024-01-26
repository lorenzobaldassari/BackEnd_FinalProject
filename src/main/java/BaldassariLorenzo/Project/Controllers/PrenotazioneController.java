package BaldassariLorenzo.Project.Controllers;

import BaldassariLorenzo.Project.Entities.Prenotazione;
import BaldassariLorenzo.Project.Entities.Utente;
import BaldassariLorenzo.Project.Payloads.PrenotazionePayloads.PrenotazioneRespondDto;
import BaldassariLorenzo.Project.Services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public List<Prenotazione> findAll(){
        return prenotazioneService.findAll();
    }
//    @GetMapping("/me")
//    public List<Prenotazione> getMyProfile(@AuthenticationPrincipal Utente currentUser) {
//        return currentUser;
//    }//   fare query fitlro per id!

    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneRespondDto prenotazioneForMySelf(@AuthenticationPrincipal Utente currentUser,@RequestParam UUID uuid) {
        System.err.println(currentUser.getUuid());
        return prenotazioneService.post(uuid,currentUser.getUuid());
    }



}
