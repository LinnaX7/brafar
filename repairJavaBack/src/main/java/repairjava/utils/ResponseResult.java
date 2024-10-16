package repairjava.utils;

public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;

    public ResponseResult() {
        this.code = 0;
    }

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setCode(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setData(T data){
        this.data = data;
    }

    public T getData(){
        return data;
    }
}
