package io.github.forezp.entity;

import java.io.Serializable;

/**
 * Created by forezp on 2019/5/2.
 */
public class SvcInstance implements Serializable{
    private static final long serialVersionUID = 3356648245119125021L;
    private String svcName;
    private String svcInstanceId;
    private String svcTag;

    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName;
    }

    public String getSvcInstanceId() {
        return svcInstanceId;
    }

    public void setSvcInstanceId(String svcInstanceId) {
        this.svcInstanceId = svcInstanceId;
    }

    public String getSvcTag() {
        return svcTag;
    }

    public void setSvcTag(String svcTag) {
        this.svcTag = svcTag;
    }
}
