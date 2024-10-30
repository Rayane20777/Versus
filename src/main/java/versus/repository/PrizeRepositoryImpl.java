package versus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import versus.model.Prize;
import versus.repository.interfaces.PrizeRepository;
import versus.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Transactional
public class PrizeRepositoryImpl implements PrizeRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrizeRepositoryImpl.class);

    @Override
    public Prize save(Prize prize) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Saving prize with amount: {}", prize.getAmount());
            entityManager.persist(prize);
            transaction.commit();
            return prize;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error saving prize: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }


} 