package shop.mtcoding.bank.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.bank.user.User;


@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public void 계좌생성(AccountRequest.SaveDTO reqDTO, User sessionUser){
        Account account = reqDTO.toEntity(sessionUser);
        accountRepository.save(reqDTO.toEntity(sessionUser));
    }
}