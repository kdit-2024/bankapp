package shop.mtcoding.bank.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bank.account.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * 목적 : 클래스를 통해 테이블을 자동생성
 */

@Setter
@Getter
@Table(name = "user_tb") // 테이블명
@Entity // 스프링시작될때, 테이블을 만들어
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 적용
    private Integer id; // PK

    @Column(unique = true)
    private String username; // 아이디
    @Column(length = 12)
    private String password; // 비밀번호
    private String email; // 이메일
    private String fullname; // 사람이름

}
