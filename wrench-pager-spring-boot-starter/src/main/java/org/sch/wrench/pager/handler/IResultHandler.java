package org.sch.wrench.pager.handler;

public interface IResultHandler<T> {
    /**
     * 从返回值中取出分页查询结果
     * @return 取到的结果，不允许返回 null，至少要返回空集合
     * @throws Exception 如果抛出任何异常，均视为处理失败
     */
    Object getData(T result) throws Exception;

    void setData(T result, Object data);
}
