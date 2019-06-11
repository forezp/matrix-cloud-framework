package io.github.forezp.entity;


import io.github.forezp.enums.LimitType;

/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-06-25
 **/
public class LimitEntity {
    /**
     *
     * Created by forezp on 2019/5/4.
     *
     * rate.limit.enable: true
     * rate.limit.global.num: 100
     * rate.limit.global.second: 1
     * rate.limit.services:
     *    - name: mcf-provider
     *      num: 10
     *      second: 11
     *    - name: mcf-consumer
     *      num: 23
     *      second: 11
     */

    private LimitType limitType;

    private String name;

    private Integer num;

    private Integer second;//多少秒

    public LimitType getLimitType() {
        return limitType;
    }

    public void setLimitType(LimitType limitType) {
        this.limitType = limitType;
    }

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
