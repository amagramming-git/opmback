package com.openmemo.opmback.mapper.post;

public class PostExampleCustom extends PostExample {

    protected Integer limit;

    protected Integer offset;

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }
}