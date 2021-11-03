package org.sch.wrench.pager.handler;

import org.sch.wrench.common.vo.result.Result;

public class DefaultResultHandler implements IResultHandler<Result<Object>> {

    @Override
    public Object getData(Result<Object> result) throws Exception {
        return result.getData();
    }

    @Override
    public void setData(Result<Object> result, Object data) {
        result.setData(data);
    }
}
