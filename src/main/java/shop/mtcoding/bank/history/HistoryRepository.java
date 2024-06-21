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

    // ajax 통신 요청왔을 때 사용하는 Repository
    public List<History> findByDetailHistory(Integer accountId, String gubun){
        
        String sql = "";
        
        if(gubun.equals("입금")){
            sql += "select hs from History hs join fetch hs.withdrawAccount join fetch hs.depositAccount where hs.depositAccount.id = :accountId";
        }else if(gubun.equals("출금")){
            sql += "select hs from History hs join fetch hs.withdrawAccount join fetch hs.depositAccount where hs.withdrawAccount.id =:accountId";
        }else{ // 전체
            sql += "select hs from History hs join fetch hs.withdrawAccount join fetch hs.depositAccount where hs.withdrawAccount.id =:accountId or hs.depositAccount.id = :accountId";
        }
        
        Query query = em.createQuery(sql, History.class);
        query.setParameter("accountId", accountId);
        return query.getResultList();
    }

//    public List<History> findByWIdOrDIdJoinAccount(Integer accountId){
//        Query query = em.createQuery("select hs from History hs join fetch hs.withdrawAccount join fetch hs.depositAccount where hs.withdrawAccount.id =:accountId or hs.depositAccount.id = :accountId", History.class);
//        query.setParameter("accountId", accountId);
//
//        return query.getResultList();
//    }

    public void save(History history){
        em.persist(history);
    }

}
