package shop.mtcoding.bank.account;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.bank.user.User;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class AccountRepository {
    private final EntityManager em;
    
    // TODO: 계좌번호로 계좌조회 필요
    public Account findByNumber(String number){
        Query query = em.createQuery("select ac from Account ac where ac.number=:number", Account.class);
        query.setParameter("number", number);

        Account account = (Account) query.getSingleResult();
        return account;
    }

    public Account findByNumberJoinUser(String number){
        Query query = em.createQuery("select ac from Account ac join fetch ac.user where ac.number=:number", Account.class);
        query.setParameter("number", number);

        Account account = (Account) query.getSingleResult();
        return account;
    }
    
    // TODO: 업데이트 메서드 필요
    

    public List<Account> findAllV2(Integer sessionUserId){

        Query query = em.createNativeQuery("select * from account_tb ac inner join user_tb u on ac.user_id = u.id where ac.user_id = ?");
        query.setParameter(1, sessionUserId);

        List<Object[]> rs = query.getResultList();

        List<Account> accountList = new ArrayList<>();

        for(Object[] obs : rs){
            System.out.println(obs[0]); // balance
            System.out.println(obs[1]); // accountId
            System.out.println(obs[2]); // number
            System.out.println(obs[3]); // userId
            System.out.println(obs[4]); // password
            System.out.println(obs[5]); // user객체의 id
            System.out.println(obs[6]); // user객체의 password
            System.out.println(obs[7]); // user객체의 email
            System.out.println(obs[8]); // user객체의 fullname
            System.out.println(obs[9]); // user객체의 username
            System.out.println("-----------------");

            Account account = new Account();
            account.setId((Integer) obs[1]);
            account.setNumber((String) obs[2]);
            account.setPassword((String) obs[4]);
            account.setBalance((Integer) obs[0]);

            User user = new User();
            user.setId((Integer) obs[5]);
            user.setUsername((String) obs[9]);
            user.setPassword((String) obs[6]);
            user.setEmail((String) obs[7]);
            user.setFullname((String) obs[8]);

            account.setUser(user);

            accountList.add(account);
        }

        return accountList;
    }

    public List<Account> findAll(Integer sessionUserId){
        // select * from account_tb ac inner join user_tb u on ac.user_id = u.id where ac.user_id = ?
        // select * from account_tb where user_id = ?
        Query query = em.createQuery("select ac from Account ac join fetch ac.user where ac.user.id = :sessionUserId", Account.class);
        query.setParameter("sessionUserId", sessionUserId);

        List<Account> accountList = query.getResultList();
        return accountList;
    }

    public void save(Account account){
        em.persist(account);
    }
}
