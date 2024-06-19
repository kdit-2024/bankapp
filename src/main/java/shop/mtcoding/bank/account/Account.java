package shop.mtcoding.bank.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bank.user.User;

@Setter
@Getter
@Table(name = "account_tb")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 4)
    private String number; // 계좌번호
    @Column(length = 12)
    private String password; // 계좌 비밀번호

    private Integer balance; // 잔액 (21억보다 많을 수 없다)

    // fk
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // hibernate - orm 기술
}

















