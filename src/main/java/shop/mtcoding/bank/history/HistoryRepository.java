package shop.mtcoding.bank.history;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.bank.account.Account;
import shop.mtcoding.bank.user.User;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class HistoryRepository {
    private final EntityManager em;

    public void save(History history){
        em.persist(history);
    }

}
