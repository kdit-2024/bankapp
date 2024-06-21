package shop.mtcoding.bank.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.bank.history.History;
import shop.mtcoding.bank.history.HistoryRepository;
import shop.mtcoding.bank.user.User;

import java.util.List;

// 트랜잭션관리(commit, rollback), 비지니스로직 처리(잔액검증, 패스워드 검증)
// 화면에 필요한 데이터만 담기
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final HistoryRepository historyRepository;

    @Transactional
    public void 계좌이체(AccountRequest.TransferDTO reqDTO){
        // 1. 출금 계좌 존재 여부
        Account wAccount = accountRepository.findByNumber(reqDTO.getWNumber());
        if(wAccount == null) throw new RuntimeException("출금계좌가 없어요");
        
        // 2. 입금 계좌 존재 여부
        Account dAccount = accountRepository.findByNumber(reqDTO.getDNumber());
        if(dAccount == null) throw new RuntimeException("입금계좌가 없어요");
        
        // 3. 출금 계좌 잔액 검증 (DB 조회가 필요) - amount 보다 더 금액이 있는지!!
        if(wAccount.getBalance() < reqDTO.getAmount()) throw new RuntimeException("잔액이 부족해요 : 현재잔액 : "+wAccount.getBalance()); 

        // 4. 출금 패스워드 검증 - password
        if(!wAccount.getPassword().equals(reqDTO.getPassword())) throw new RuntimeException("출금계좌의 패스워드가 일치하지 않습니다");
        
        // 5. 출금 계좌 업데이트 (객체 상태 변경)
        wAccount.setBalance(wAccount.getBalance() - reqDTO.getAmount());
        
        // 6. 입금 계좌 업데이트 (객체 상태 변경)
        dAccount.setBalance(dAccount.getBalance() + reqDTO.getAmount());
        
        // 7. 히스토리 인서트
        History history = new History();
        history.setWithdrawAccount(wAccount);
        history.setDepositAccount(dAccount);
        history.setWithdrawBalance(wAccount.getBalance());
        history.setDepositBalance(dAccount.getBalance());
        history.setAmount(reqDTO.getAmount());

        historyRepository.save(history);
    } // 영속화 된 객체의 상태가 변경되면, update가 일어난다. (더티체킹)
    
    
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

    public AccountResponse.DetailDTO 계좌상세보기(String number, Integer sessionUserId) {
        // 1. number로 계좌조회 하기 (User Join해서 가져와야함)
        Account account = accountRepository.findByNumberJoinUser(number);
        if(account == null) throw new RuntimeException("조회할 계좌가 없어요");

        // 2. 권한체크
        if(!account.getUser().getId().equals(sessionUserId)) throw new RuntimeException("해당 계좌를 조회할 권한이 없어요");

        // 3. number -> id로 계좌히스토리 조회하기
        List<History> historyList = historyRepository.findByDetailHistory(account.getId(), "전체");

        
        // 4. Account객체, List<History> 객체 -> 합쳐서 리턴
        return new AccountResponse.DetailDTO(account, historyList);
    }

    public AccountResponse.DetailDTOV3 계좌상세보기V3(String number, Integer sessionUserId) {
        Account account = accountRepository.findByNumberJoinHistoryV2(number);
        if(account == null) throw new RuntimeException("조회할 계좌가 없어요");

        if(!account.getUser().getId().equals(sessionUserId)) throw new RuntimeException("해당 계좌를 조회할 권한이 없어요");

        // 3. Account객체, List<History> 객체 -> 합쳐서 리턴
        return new AccountResponse.DetailDTOV3(account);
    }

    public List<AccountResponse.HistoryDTO> 계좌히스토리(String number, String gubun) {
        Account account = accountRepository.findByNumber(number);
        if(account == null) throw new RuntimeException("조회할 계좌가 없어요");
        
        // 세션정보 받아서 권한체크 해야함
        
        List<History> histories = historyRepository.findByDetailHistory(account.getId(), gubun);
        return histories.stream().map(history -> new AccountResponse.HistoryDTO(history, account.getNumber())).toList();
    }
}




