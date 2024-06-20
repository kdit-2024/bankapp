package shop.mtcoding.bank.pratice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class StreamJoinTest {
    
    @Test
    public void join_test(){
        List<Integer> n1 = Arrays.asList(1,3,5,7,10);
        List<Integer> n2 = Arrays.asList(2,4,6,8,11);
        
        // stream api n1+n2
    }

    @Test
    public void join_order_test(){
        List<Integer> n1 = Arrays.asList(1,3,5,7,10);
        List<Integer> n2 = Arrays.asList(2,4,6,8,11);

        // stream api n1+n2 -> 합쳐서 order 하기
    }

    @Test
    public void join_filter_test(){
        List<Integer> n1 = Arrays.asList(1,3,5,7,10);
        List<Integer> n2 = Arrays.asList(2,4,6,8,11);

        // stream api n1+n2 -> 홀수를 날리고 합치기
    }
}
