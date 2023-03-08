package tz.go.bot.payload;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiSuccessPayload {

    private  String message;
    private int statusCode;
    private String httpStatus;
    private boolean success;
    private boolean exception;
    private Object body;
    private LocalDateTime timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isException() {
        return exception;
    }

    public void setException(boolean exception) {
        this.exception = exception;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static ApiSuccessPayload build(Object body, HttpStatus status,String message){
        ApiSuccessPayload payload=new ApiSuccessPayload();

        payload.setSuccess(true);
        payload.setException(false);
        payload.setBody(body);
        payload.setMessage(message);
        payload.setStatusCode(status.value());
        payload.setHttpStatus(String.valueOf(status));
        payload.setTimestamp(LocalDateTime.now());

        return payload;
    }

}
