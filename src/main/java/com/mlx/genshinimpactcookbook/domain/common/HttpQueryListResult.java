package com.mlx.genshinimpactcookbook.domain.common;

import java.util.List;
import java.util.StringJoiner;

public class HttpQueryListResult extends HttpMessage{
    private List<?> rows;
    private int total;

    public HttpQueryListResult(){};

    public HttpQueryListResult(int code, String msg, List<?> rows, int total) {
        super(code, msg);
        this.rows = rows;
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HttpQueryListResult.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("msg='" + msg + "'")
                .add("rows=" + rows)
                .add("total=" + total)
                .toString();
    }
}
