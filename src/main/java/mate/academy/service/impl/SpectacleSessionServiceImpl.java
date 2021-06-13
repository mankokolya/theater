package mate.academy.service.impl;

import java.time.LocalDate;
import java.util.List;
import mate.academy.dao.SpectacleSessionDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.SpectacleSession;
import mate.academy.service.SpectacleSessionService;

@Service
public class SpectacleSessionServiceImpl implements SpectacleSessionService {
    @Inject
    private SpectacleSessionDao sessionDao;

    @Override
    public List<SpectacleSession> findAvailableSessions(Long movieId, LocalDate date) {
        return sessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public SpectacleSession get(Long id) {
        return sessionDao.get(id).get();
    }

    @Override
    public SpectacleSession add(SpectacleSession session) {
        return sessionDao.add(session);
    }
}
