package mate.academy.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.model.Spectacle;

public interface SpectacleDao {
    Spectacle add(Spectacle spectacle);

    Optional<Spectacle> get(Long id);

    List<Spectacle> getAll();
}
