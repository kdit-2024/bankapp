package shop.mtcoding.bank.util;

import lombok.Data;

@Data
public class ApiUtil<T> {
    private Integer status;
    private String msg;
    private T body;

    public ApiUtil(Integer status, String msg, T body) {
        this.status = status;
        this.msg = msg;
        this.body = body;
    }
}
