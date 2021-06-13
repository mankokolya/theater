package mate.academy.service;

import java.util.List;
import mate.academy.model.Spectacle;

public interface SpectacleService {
    Spectacle add(Spectacle spectacle);

    Spectacle get(Long id);

    List<Spectacle> getAll();
}
