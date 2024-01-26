package BaldassariLorenzo.Project.Controllers;

import BaldassariLorenzo.Project.Entities.Evento;
import BaldassariLorenzo.Project.Exceptions.BadRequestException;
import BaldassariLorenzo.Project.Payloads.EventoPayloads.EventoRequestDto;
import BaldassariLorenzo.Project.Payloads.EventoPayloads.EventoRespondDto;
import BaldassariLorenzo.Project.Services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    EventoService eventoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Evento> getList(){return eventoService.findAll();}

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.CREATED)
    public EventoRespondDto post(@RequestBody @Validated EventoRequestDto body,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            throw new BadRequestException("errore nel invio del payload per il metodo POST"+bindingResult.getAllErrors());
        } else {
            return eventoService.postEvento(body);
        }
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Evento findByID(@PathVariable UUID id){
        return eventoService.findById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.OK)
    public EventoRespondDto modify(@RequestBody @Validated EventoRequestDto body,
                                   @PathVariable UUID id, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            throw new BadRequestException("errore nel invio del payload per il metodo POST"+bindingResult.getAllErrors());
        } else {
            return eventoService.modify(id,body);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        eventoService.delete(id);
    }

}
