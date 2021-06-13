package mate.academy.service;

import java.time.LocalDate;
import java.util.List;
import mate.academy.model.SpectacleSession;

public interface SpectacleSessionService {
    List<SpectacleSession> findAvailableSessions(Long movieId, LocalDate date);

    SpectacleSession get(Long id);

    SpectacleSession add(SpectacleSession session);
}
