package shop.mtcoding.bank.util;

public class CommonUtil {

    public static String formatMoney(Integer number){
        return String.format("%,d", number);
    }
}
