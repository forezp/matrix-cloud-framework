package io.github.forezp.exception;

/**
 * Created by forezp on 2019/5/2.
 */

public class McfException extends RuntimeException {
    private static final long serialVersionUID = 7975167663357170655L;

    public McfException() {
        super();
    }

    public McfException(String message) {
        super(message);
    }

    public McfException(String message, Throwable cause) {
        super(message, cause);
    }

    public McfException(Throwable cause) {
        super(cause);
    }
}

