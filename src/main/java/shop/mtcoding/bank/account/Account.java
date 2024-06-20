package shop.mtcoding.bank.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bank.history.History;
import shop.mtcoding.bank.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // fk 가 아니라, 조회를 위해서 사용!!
    @OneToMany(mappedBy = "withdrawAccount", fetch = FetchType.LAZY)
    private Set<History> withdrawHistories = new HashSet<>(); // 양방향 매핑

    @OneToMany(mappedBy = "depositAccount", fetch = FetchType.LAZY)
    private Set<History> depositHistories = new HashSet<>(); // 양방향 매핑
}

















