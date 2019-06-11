package io.github.forezp.test;

/**
 * Created by forezp on 2019/5/4.
 */
public class GlobalLimitRule {

    private String num;
    private String second;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
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
