package shop.mtcoding.bank.history;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.bank.account.Account;

import java.sql.Timestamp;

@Setter
@Getter
@Table(name = "history_tb", indexes = {
        @Index(name = "idx_withdraw_account", columnList = "withdraw_account_id"),
        @Index(name = "idx_deposit_account", columnList = "deposit_account_id"),
})
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account withdrawAccount; // 출금 계좌 (보낸 계좌번호)

    @ManyToOne(fetch = FetchType.LAZY)
    private Account depositAccount; // 입금 계좌 (받은 계좌번호)

    private Integer amount; // 이체 금액
    
    private Integer withdrawBalance; // 출금 계좌 잔액
    private Integer depositBalance; // 입금 계좌 잔액

    @CreationTimestamp // insert 할 때, 현재시간 들어감
    private Timestamp createdAt;
}
