package io.github.forezp.entity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by forezp on 2019/5/1.
 */
public class LimitCollectData {

    private String name;
    private AtomicInteger access;
    private AtomicInteger refuse;

    public void accessIncrement(){
        access.incrementAndGet();
    }
    public void refuseIncrement(){
        access.incrementAndGet();
    }

    public void reset(){
        access.set(0);
        refuse.set(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AtomicInteger getAccess() {
        return access;
    }

    public void setAccess(AtomicInteger access) {
        this.access = access;
    }

    public AtomicInteger getRefuse() {
        return refuse;
    }

    public void setRefuse(AtomicInteger refuse) {
        this.refuse = refuse;
    }

    @Override
    public String toString() {
        return "LimitCollectData{" +
                "name='" + name + '\'' +
                ", access=" + access +
                ", refuse=" + refuse +
                '}';
    }
}
