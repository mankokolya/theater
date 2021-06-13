package mate.academy.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie_sessions")
public class SpectacleSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Spectacle spectacle;
    @ManyToOne(fetch = FetchType.LAZY)
    private TheatreHall theatreHall;
    private LocalDateTime showTime;

    public SpectacleSession() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Spectacle getMovie() {
        return spectacle;
    }

    public void setMovie(Spectacle spectacle) {
        this.spectacle = spectacle;
    }

    public TheatreHall getCinemaHall() {
        return theatreHall;
    }

    public void setCinemaHall(TheatreHall theatreHall) {
        this.theatreHall = theatreHall;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "SpectacleSession{"
                + "id=" + id
                + ", spectacle=" + spectacle
                + ", theatreHall=" + theatreHall
                + ", showTime=" + showTime
                + '}';
    }
}
