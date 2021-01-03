package io.github.forezp.client.dto;

import java.util.List;

public class SvcDto {

    /**
     * {
     *     "count":148,
     *     "doms": [
     *         "nacos.test.1",
     *         "nacos.test.2"
     *     ]
     * }
     */

    private Integer count;

    private List<String> doms;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<String> getDoms() {
        return doms;
    }

    public void setDoms(List<String> doms) {
        this.doms = doms;
    }
}
