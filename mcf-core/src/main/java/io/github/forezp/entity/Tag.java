package io.github.forezp.entity;

public class Tag {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Tag(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Tag() {

    }

    @Override
    public String toString() {
        return "Tag{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
