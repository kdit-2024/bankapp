package shop.mtcoding.bank.account;

import lombok.Data;
import shop.mtcoding.bank.user.User;
import shop.mtcoding.bank.util.CommonUtil;

import java.util.List;

public class AccountResponse {

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
