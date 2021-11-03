package org.pear.wrench.pager.handler;

import org.pear.wrench.common.vo.result.Result;

public class SchResultHandler implements IResultHandler<Result<Object>> {

    @Override
    public Object getData(Result<Object> result) throws Exception {
        return result.getData();
    }

    @Override
    public void setData(Result<Object> result, Object data) {
        result.setData(data);
    }
}
