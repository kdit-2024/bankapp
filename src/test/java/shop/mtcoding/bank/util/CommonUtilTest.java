package shop.mtcoding.bank.util;

import org.junit.jupiter.api.Test;

public class CommonUtilTest {

    @Test
    public void format_test(){
        Integer balance = 1000;
        String formattedNumber = String.format("%,d", balance);
        System.out.println(formattedNumber);
    }
}
