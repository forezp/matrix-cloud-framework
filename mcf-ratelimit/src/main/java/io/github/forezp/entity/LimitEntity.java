package io.github.forezp.entity;


/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-06-25
 **/
public class LimitEntity {



    private String name;

    private Integer num;

    private Integer second;//多少秒



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
}
