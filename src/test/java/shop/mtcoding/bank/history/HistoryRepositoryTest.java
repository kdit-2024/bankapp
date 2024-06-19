package shop.mtcoding.bank.history;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(HistoryRepository.class)
@DataJpaTest
public class HistoryRepositoryTest {

    @Autowired
    private HistoryRepository historyRepository;

    @Test
    public void findByWIdOrDId_test(){
        Integer accountId = 1;

        historyRepository.findByWIdOrDIdJoinAccount(accountId);
    }
}
