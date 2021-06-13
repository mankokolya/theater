package mate.academy.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private SpectacleSession spectacleSession;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Ticket() {
    }

    public Ticket(User user, SpectacleSession spectacleSession) {
        this.user = user;
        this.spectacleSession = spectacleSession;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpectacleSession getMovieSession() {
        return spectacleSession;
    }

    public void setMovieSession(SpectacleSession spectacleSession) {
        this.spectacleSession = spectacleSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{"
                + "id=" + id
                + ", spectacleSession=" + spectacleSession
                + ", user=" + user
                + '}';
    }
}
