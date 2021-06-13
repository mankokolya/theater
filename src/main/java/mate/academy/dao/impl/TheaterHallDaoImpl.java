package mate.academy.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import mate.academy.dao.TheatreHallDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.TheatreHall;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TheaterHallDaoImpl implements TheatreHallDao {
    @Override
    public TheatreHall add(TheatreHall theatreHall) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(theatreHall);
            transaction.commit();
            return theatreHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert theatre hall: " + theatreHall, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<TheatreHall> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(TheatreHall.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a theatre hall by id: " + id, e);
        }
    }

    @Override
    public List<TheatreHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<TheatreHall> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(TheatreHall.class);
            criteriaQuery.from(TheatreHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all theatre halls", e);
        }
    }
}
