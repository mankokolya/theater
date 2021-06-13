package mate.academy.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import mate.academy.model.SpectacleSession;

public interface SpectacleSessionDao {
    List<SpectacleSession> findAvailableSessions(Long spectacleId, LocalDate date);

    Optional<SpectacleSession> get(Long id);

    SpectacleSession add(SpectacleSession session);
}
