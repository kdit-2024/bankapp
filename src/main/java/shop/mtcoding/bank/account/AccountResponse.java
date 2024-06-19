package shop.mtcoding.bank.account;

import lombok.Data;
import shop.mtcoding.bank.history.History;
import shop.mtcoding.bank.user.User;
import shop.mtcoding.bank.util.CommonUtil;

import java.util.List;

public class AccountResponse {

    @Data
    public static class DetailDTO {
        private String fullname; // 이름
        private String myNumber; // 계좌번호
        private Integer currentBalance; // 현재 잔액
        private List<HistoryDTO> histories; // 이체 내역들

        public DetailDTO(Account account, List<History> histories) {
            this.fullname = account.getUser().getFullname();
            this.myNumber = account.getNumber();
            this.currentBalance = account.getBalance();
            this.histories = histories.stream().map(HistoryDTO::new).toList();
        }

        @Data
        class HistoryDTO {
            private String createdAt;
            private String senderNumber;  // 보낸사람 계좌번호
            private String receiverNumber; // 받은사람 계좌번호
            private Integer amount; // 이체금액
            private Integer balance; // 과거잔액 (보낸사람 잔액 or 받은사람 잔액)
            private String gubun; // 입금, 출금

            public HistoryDTO(History history) {
                this.createdAt = history.getCreatedAt().toString();
                this.senderNumber = history.getWithdrawAccount().getNumber();
                this.receiverNumber = history.getDepositAccount().getNumber();
                this.amount = history.getAmount();

                // myNumber가 senderNumber 같으면 withDrawBalance
                // myNumber가 receiveNumber 같으면 depositBalance

                this.balance = myNumber == senderNumber ? history.getWithdrawBalance() : history.getDepositBalance();
                this.gubun = myNumber == senderNumber ? "출금" : "입금";
            }
        }

    }

    // account 객체를 -> ListDTO 객체로 옮기는것
    @Data
    public static class ListDTO {
        private String fullname;
        private List<AccountDTO> accounts;

        public ListDTO(List<Account> accountList) {
            this.fullname = accountList.get(0).getUser().getFullname();
            this.accounts = accountList.stream().map(AccountDTO::new).toList();
            //this.accounts = accountList.stream().map(account -> new AccountDTO(account)).toList();
        }

        @Data
        public class AccountDTO {
            private Integer accountId;
            private String number; // uk
            private String balance;

            public AccountDTO(Account account) {
                this.accountId = account.getId();
                this.number = account.getNumber();
                this.balance = CommonUtil.formatMoney(account.getBalance());
            }
        }
    }
}
