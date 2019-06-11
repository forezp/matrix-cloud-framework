package io.github.forezp.entity;

/**
 * Created by forezp on 2019/5/4.
 */
public class GlobalLimitRule {

    private Integer num;
    private Integer second;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "GlobalLimitRule{" +
                "num='" + num + '\'' +
                ", second='" + second + '\'' +
                '}';
    }
}
