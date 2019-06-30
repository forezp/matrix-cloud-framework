package io.github.forezp.entity;

/**
 * Created by forezp on 2019/5/4.
 */
public class RatelimitRule {

    private String name;
    private Integer num;
    private Integer second;
    private RatelimitRuleType ratelimitRuleType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public RatelimitRuleType getRatelimitRuleType() {
        return ratelimitRuleType;
    }

    public void setRatelimitRuleType(RatelimitRuleType ratelimitRuleType) {
        this.ratelimitRuleType = ratelimitRuleType;
    }

    @Override
    public String toString() {
        return "LimitRule{" +
                "name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", second='" + second + '\'' +
                '}';
    }

    public  enum RatelimitRuleType{
        GLOBAL,SERVICE
    }
}
