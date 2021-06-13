package mate.academy.service.impl;

import java.util.List;
import mate.academy.dao.SpectacleDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.Spectacle;
import mate.academy.service.SpectacleService;

@Service
public class SpectacleServiceImpl implements SpectacleService {
    @Inject
    private SpectacleDao spectacleDao;

    @Override
    public Spectacle add(Spectacle spectacle) {
        return spectacleDao.add(spectacle);
    }

    @Override
    public Spectacle get(Long id) {
        return spectacleDao.get(id).get();
    }

    @Override
    public List<Spectacle> getAll() {
        return spectacleDao.getAll();
    }
}
