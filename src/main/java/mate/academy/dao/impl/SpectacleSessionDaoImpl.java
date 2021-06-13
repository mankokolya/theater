package mate.academy.dao.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mate.academy.dao.SpectacleSessionDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.SpectacleSession;
import mate.academy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class SpectacleSessionDaoImpl implements SpectacleSessionDao {
    private static final LocalTime END_OF_DAY = LocalTime.of(23, 59, 59);

    @Override
    public SpectacleSession add(SpectacleSession spectacleSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(spectacleSession);
            transaction.commit();
            return spectacleSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert spectacle session" + spectacleSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<SpectacleSession> findAvailableSessions(Long spectacleId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<SpectacleSession> criteriaQuery =
                    criteriaBuilder.createQuery(SpectacleSession.class);
            Root<SpectacleSession> root = criteriaQuery.from(SpectacleSession.class);
            Predicate spectaclePredicate = criteriaBuilder.equal(root.get("spectacle"), spectacleId);
            Predicate datePredicate = criteriaBuilder.between(root.get("showTime"),
                    date.atStartOfDay(), date.atTime(END_OF_DAY));
            Predicate allConditions = criteriaBuilder.and(spectaclePredicate, datePredicate);
            criteriaQuery.select(root).where(allConditions);
            root.fetch("spectacle");
            root.fetch("theatreHall");
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get available sessions for spectacle with id: "
                    + spectacleId + " for date: " + date, e);
        }
    }

    @Override
    public Optional<SpectacleSession> get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from SpectacleSession ss "
                    + "left join fetch ss.spectacle "
                    + "left join fetch ss.theatreHall "
                    + "where ss.id = :id", SpectacleSession.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a spectacle session by id: " + id, e);
        }
    }
}
