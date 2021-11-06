package io.github.maodua.wrench.starter.handler;


import io.github.maodua.wrench.pager.handler.IResultHandler;

public class RResultHandler implements IResultHandler<R<Object>> {

    @Override
    public Object getData(R<Object> result) throws Exception {
        return result.getData();
    }

    @Override
    public void setData(R<Object> result, Object data) {
        result.setData(data);
    }
}
