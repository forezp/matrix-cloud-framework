package io.github.forezp.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by forezp on 2019/5/2.
 */
public class McfMetaData implements Serializable{

    private static final long serialVersionUID = 3356648245119125011L;

    private SvcInstance svcSource;
    private String svcTarget;
    private String[] svcTargetTags;
    private String userId;
    private String requstId;

    public SvcInstance getSvcSource() {
        return svcSource;
    }

    public void setSvcSource(SvcInstance svcSource) {
        this.svcSource = svcSource;
    }

    public String getSvcTarget() {
        return svcTarget;
    }

    public void setSvcTarget(String svcTarget) {
        this.svcTarget = svcTarget;
    }

    public String[] getSvcTargetTags() {
        return svcTargetTags;
    }

    public void setSvcTargetTags(String[] svcTargetTags) {
        this.svcTargetTags = svcTargetTags;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "McfMetaData{" +
                "svcSource=" + svcSource +
                ", svcTarget='" + svcTarget + '\'' +
                ", svcTargetTags=" + Arrays.toString(svcTargetTags) +
                ", userId='" + userId + '\'' +
                ", requstId='" + requstId + '\'' +
                '}';
    }
}
