package shop.mtcoding.bank.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.mtcoding.bank.history.HistoryRepository;
import shop.mtcoding.bank.user.User;

@ExtendWith(MockitoExtension.class) // 가짜 IoC 세상을 만든다
public class AccountServiceTest {

    @InjectMocks // accountService는 진짜 객체로 올린다.
    private AccountService accountService;

    @Mock // accountRepository 가짜 객체로 올린다.
    private AccountRepository accountRepository;

    @Mock // historyRepository 가짜 객체로 올린다.
    private HistoryRepository historyRepository;

    @Test
    public void 계좌이체V2_test(){
        AccountRequest.TransferDTO reqDTO = new AccountRequest.TransferDTO();
        reqDTO.setWNumber("1111");
        reqDTO.setDNumber("2222");
        reqDTO.setPassword("1234");
        reqDTO.setAmount(100);

        User ssar = new User();
        ssar.setId(1);
        ssar.setUsername("ssar");
        ssar.setPassword("1234");
        ssar.setEmail("ssar@nate.com");
        ssar.setFullname("쌀");

        Account wAccount = new Account();
        wAccount.setId(1);
        wAccount.setNumber("1111");
        wAccount.setPassword("1234");
        wAccount.setBalance(1000);
        wAccount.setUser(ssar);

        Account dAccount = new Account();
        dAccount.setId(2);
        dAccount.setNumber("22");
        dAccount.setPassword("1234");
        dAccount.setBalance(1000);
        dAccount.setUser(ssar);

        Mockito.when(accountRepository.findByNumber("1111")).thenReturn(wAccount);
        Mockito.when(accountRepository.findByNumber("2222")).thenReturn(dAccount);

        accountService.계좌이체(reqDTO);
    }


    @Test
    public void 계좌이체_test(){
        // given
        AccountRequest.TransferDTO reqDTO = new AccountRequest.TransferDTO();
        reqDTO.setWNumber("1111");
        reqDTO.setDNumber("2222");
        reqDTO.setPassword("1234");
        reqDTO.setAmount(100);

        User ssar = new User();
        ssar.setId(1);
        ssar.setUsername("ssar");
        ssar.setPassword("1234");
        ssar.setEmail("ssar@nate.com");
        ssar.setFullname("쌀");

        Account wAccount = new Account();
        wAccount.setId(1);
        wAccount.setNumber("1111");
        wAccount.setPassword("1234");
        wAccount.setBalance(1000);
        wAccount.setUser(ssar);

        Account dAccount = new Account();
        dAccount.setId(2);
        dAccount.setNumber("22");
        dAccount.setPassword("1234");
        dAccount.setBalance(1000);
        dAccount.setUser(ssar);

        // when
        if(wAccount.getBalance() < reqDTO.getAmount()) throw new RuntimeException("잔액이 부족해요 : 현재잔액 : "+wAccount.getBalance());

        // 4. 출금 패스워드 검증 - password
        if(!wAccount.getPassword().equals(reqDTO.getPassword())) throw new RuntimeException("출금계좌의 패스워드가 일치하지 않습니다");

        // 5. 출금 계좌 업데이트 (객체 상태 변경)
        wAccount.setBalance(wAccount.getBalance() - reqDTO.getAmount());

        // 6. 입금 계좌 업데이트 (객체 상태 변경)
        dAccount.setBalance(dAccount.getBalance() + reqDTO.getAmount());

        // then
        System.out.println(wAccount.getBalance()); // 900
        System.out.println(dAccount.getBalance()); /// 1100
    }
}
