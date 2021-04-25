package io.github.forezp.entity;

import java.util.List;

public class AuthRule {
    Boolean enable;
    List<AuthInstance> blacks;
    List<AuthInstance> whites;


    public List<AuthInstance> getBlacks() {
        return blacks;
    }

    public void setBlacks(List<AuthInstance> blacks) {
        this.blacks = blacks;
    }

    public List<AuthInstance> getWhites() {
        return whites;
    }

    public void setWhites(List<AuthInstance> whites) {
        this.whites = whites;
    }


    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
