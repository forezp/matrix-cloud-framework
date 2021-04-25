package io.github.forezp.entity;

/**
 * Created by forezp on 2019/5/1.
 */
public class LimitResult {



    private String name;

    private ResultType resultType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public enum ResultType{
        SUCCESS,FAIL
    }
}
