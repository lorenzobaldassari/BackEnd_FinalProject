package BaldassariLorenzo.Project.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@JsonIgnoreProperties({"password", "authorities", "accountNonExpired",
        "enabled", "accountNonLocked", "credentialsNonExpired"})
public class Utente implements UserDetails {

    @Id
    @GeneratedValue
    private UUID uuid;

    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> listaDiPrenotazioni;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "uuid=" + uuid +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
