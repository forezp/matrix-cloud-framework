package io.github.forezp.test;

/**
 * Created by forezp on 2019/5/4.
 */
public class LimitRule {

    private String name;
    private String num;
    private String second;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        return "LimitRule{" +
                "name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", second='" + second + '\'' +
                '}';
    }
}
