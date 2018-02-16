package br.com.darisson.gitview.event;

/**
 *Evento quando o request dá errado.
*/

public class RequestFailedEvent {
    public Integer getErr_message() {
        return err_message;
    }
    private Integer err_message;
    private String message;
    private boolean defaultError;

    public String getMessage() {
        return message;
    }

    public boolean isDefaultError() {
        return defaultError;
    }

    public RequestFailedEvent(String message, boolean defaultError, Integer err_message) {
        this.err_message = err_message;
        this.message = message;
        this.defaultError = defaultError;
    }

}
