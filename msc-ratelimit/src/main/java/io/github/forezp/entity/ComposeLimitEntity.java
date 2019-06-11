package io.github.forezp.entity;

import java.util.List;

/**
 * Created by forezp on 2019/6/7.
 */
public class ComposeLimitEntity {

    private LimitEntity globalLimit;
    private List<LimitEntity> seviceLimit;

    public LimitEntity getGlobalLimit() {
        return globalLimit;
    }

    public void setGlobalLimit(LimitEntity globalLimit) {
        this.globalLimit = globalLimit;
    }

    public List<LimitEntity> getSeviceLimit() {
        return seviceLimit;
    }

    public void setSeviceLimit(List<LimitEntity> seviceLimit) {
        this.seviceLimit = seviceLimit;
    }
}
