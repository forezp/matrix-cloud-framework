package io.github.forezp.entity;



/**
 * Created by forezp on 2019/5/2.
 */
public class AuthCheckResult {

    private ResultType resultType;


    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    @Override
    public String toString() {
        return "AuthCheckResult{" +
                "resultType=" + resultType +
                '}';
    }

    public enum  ResultType{
        ACESS,REFUSE
    }
}
