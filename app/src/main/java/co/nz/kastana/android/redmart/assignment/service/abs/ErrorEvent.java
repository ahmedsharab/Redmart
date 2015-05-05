package co.nz.kastana.android.redmart.assignment.service.abs;

public interface ErrorEvent {
    int getCode();
    String getMessage();
    void setCode(int code);
    void setMessage(String message);
}
