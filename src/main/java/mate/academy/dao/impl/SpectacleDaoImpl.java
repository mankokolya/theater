package mate.academy.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import mate.academy.dao.SpectacleDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Spectacle;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class SpectacleDaoImpl implements SpectacleDao {
    @Override
    public Spectacle add(Spectacle spectacle) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(spectacle);
            transaction.commit();
            return spectacle;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert spectacle " + spectacle, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Spectacle> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Spectacle.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a spectacle by id: " + id, e);
        }
    }

    @Override
    public List<Spectacle> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Spectacle> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Spectacle.class);
            criteriaQuery.from(Spectacle.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all spectacles", e);
        }
    }
}
