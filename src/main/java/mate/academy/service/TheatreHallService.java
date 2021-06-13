package mate.academy.service;

import java.util.List;
import mate.academy.model.TheatreHall;

public interface TheatreHallService {
    TheatreHall add(TheatreHall theatreHall);

    TheatreHall get(Long id);
    
    List<TheatreHall> getAll();
}
