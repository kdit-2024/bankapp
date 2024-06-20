package shop.mtcoding.bank.account.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class DetailDBResponse {
    private Integer accountId;
    private String accountNumber;
    private Integer accountBalance;
    private String accountPassword;
    private Integer userId;
    private Integer historyId;
    private Integer withdrawAccountId;
    private Integer depositAccountId;
    private Integer withdrawBalance;
    private Integer depositBalance;
    private Integer amount;
    private Timestamp createdAt;

    public DetailDBResponse(Integer accountId, String accountNumber, Integer accountBalance, String accountPassword, Integer userId, Integer historyId, Integer withdrawAccountId, Integer depositAccountId, Integer withdrawBalance, Integer depositBalance, Integer amount, Timestamp createdAt) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountPassword = accountPassword;
        this.userId = userId;
        this.historyId = historyId;
        this.withdrawAccountId = withdrawAccountId;
        this.depositAccountId = depositAccountId;
        this.withdrawBalance = withdrawBalance;
        this.depositBalance = depositBalance;
        this.amount = amount;
        this.createdAt = createdAt;
    }
}
