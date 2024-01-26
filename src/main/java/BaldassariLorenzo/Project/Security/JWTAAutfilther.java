package BaldassariLorenzo.Project.Security;

import BaldassariLorenzo.Project.Exceptions.UnauthorizedException;
import LorenzoBaldassari.Week6_Project.Entities.Utente;
import LorenzoBaldassari.Week6_Project.Exceptions.UnauthorizedException;
import LorenzoBaldassari.Week6_Project.Services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTAAutfilther extends OncePerRequestFilter {
    @Autowired
    private JWTTtools jwtTools;
    @Autowired
    private UtenteService utenteService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException , IOException {
            String checkRequestToken=request.getHeader("Authorization");  //baere mkafkamzmSmqm2341324qkmksamwm;22
        if(checkRequestToken==null){
            throw  new UnauthorizedException("token non presente");
        }else{
            String accessToken= checkRequestToken.substring(7);//servhe per fare lo slice del token e togliere
//            //la parola baerer ed avere il token pulito
            jwtTools.verifyToken(accessToken);  //verifichiamo il token

            // 3.2 Informo Spring Security che l'utente è autenticato (se non faccio questo passaggio continuerò ad avere 403 come risposte)
            String id = jwtTools.extractIdFromToken(accessToken);
            Utente utente= utenteService.findById(UUID.fromString(id));

            // OBBLIGATORIO aggiungere l'elenco ruoli se si vuole "attivare" il meccanismo di Autorizzazione
            Authentication authentication= new UsernamePasswordAuthenticationToken(utente,null,utente.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request,response);

        }
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Questo metodo serve per specificare quando il filtro NON deve entrare in azione
        // Ad esempio tutte le richieste al controller /auth non devono essere controllate dal filtro

        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }

}
