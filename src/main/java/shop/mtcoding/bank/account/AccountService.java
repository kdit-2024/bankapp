package shop.mtcoding.bank.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.bank.user.User;

import java.util.List;

// 트랜잭션관리(commit, rollback), 비지니스로직 처리(잔액검증, 패스워드 검증)
// 화면에 필요한 데이터만 담기
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    
    public AccountResponse.ListDTO 나의계좌목록(Integer sessionUserId){
        List<Account> accountList = accountRepository.findAll(sessionUserId);
        return new AccountResponse.ListDTO(accountList);
    }

    public List<Account> 나의계좌목록V3(Integer sessionUserId){
        List<Account> accountList = accountRepository.findAll(sessionUserId);
        return accountList;
    }

    @Transactional
    public void 계좌생성(AccountRequest.SaveDTO reqDTO, User sessionUser){
        Account account = reqDTO.toEntity(sessionUser);
        accountRepository.save(account);
    }
}