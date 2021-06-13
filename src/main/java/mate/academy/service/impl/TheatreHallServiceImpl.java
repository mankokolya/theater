package mate.academy.service.impl;

import java.util.List;
import mate.academy.dao.TheatreHallDao;
import mate.academy.lib.Inject;
import mate.academy.lib.Service;
import mate.academy.model.TheatreHall;
import mate.academy.service.TheatreHallService;

@Service
public class TheatreHallServiceImpl implements TheatreHallService {
    @Inject
    private TheatreHallDao theatreHallDao;

    @Override
    public TheatreHall add(TheatreHall theatreHall) {
        return theatreHallDao.add(theatreHall);
    }

    @Override
    public TheatreHall get(Long id) {
        return theatreHallDao.get(id).get();
    }

    @Override
    public List<TheatreHall> getAll() {
        return theatreHallDao.getAll();
    }
}
