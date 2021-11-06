package io.github.maodua.wrench.pager.handler;


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
