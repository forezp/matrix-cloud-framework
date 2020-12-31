package io.github.forezp.common.exception;

public class MatrixException extends RuntimeException {

    private ErrorCode errorCode;

    public MatrixException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public MatrixException(ErrorCode errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }


    public int getCode() {
        return errorCode.getCode();
    }

    public String getMsg() {
        return errorCode.getMsg();
    }

}
