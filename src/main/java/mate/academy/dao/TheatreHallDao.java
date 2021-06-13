package mate.academy.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.model.TheatreHall;

public interface TheatreHallDao {
    TheatreHall add(TheatreHall theatreHall);

    Optional<TheatreHall> get(Long id);

    List<TheatreHall> getAll();
}
