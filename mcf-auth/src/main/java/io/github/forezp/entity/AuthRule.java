package io.github.forezp.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 规则：黑名单或者白名单，互斥
 * Created by forezp on 2019/5/2.
 */
public class AuthRule implements Serializable{

    private static final long serialVersionUID = 3346648245119125021L;

    private Map<String,List<SvcInstance>> blackMap;
    private  Map<String,List<SvcInstance>> whiteMap;

    public Map<String, List<SvcInstance>> getBlackMap() {
        return blackMap;
    }

    public void setBlackMap(Map<String, List<SvcInstance>> blackMap) {
        this.blackMap = blackMap;
    }


    public Map<String, List<SvcInstance>> getWhiteMap() {
        return whiteMap;
    }

    public void setWhiteMap(Map<String, List<SvcInstance>> whiteMap) {
        this.whiteMap = whiteMap;
    }
}
