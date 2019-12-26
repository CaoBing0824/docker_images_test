
package com.xy.boot.open.util.result;

/**
 * @Author: heyuancheng@mfexcel.com
 * @Date: 2018-12-29 上午 11:50
 */
public class BasicRespMessage implements BasicResponse {
    protected int status = 0;
    protected String desc;
    protected Object body;

    public BasicRespMessage() {
        this.autoFilled(0);
    }

    public BasicRespMessage(int status) {
        this.autoFilled(status);
    }

    public BasicRespMessage(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public BasicRespMessage autoFilled(int status) {
        this.status = status;
        this.desc = ResultDesciptionHash.getDescription(this.status);
        return this;
    }

    public BasicRespMessage autoFilled(boolean bl) {
        if (bl) {
            this.status = 1;
        }

        this.desc = ResultDesciptionHash.getDescription(this.status);
        return this;
    }

    public BasicRespMessage autoFilled(int status, String desc) {
        this.status = status;
        this.desc = desc;
        return this;
    }

    public BasicRespMessage autoFilled(int status, Object body) {
        this.autoFilled(status);
        this.body = body;
        return this;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public int getRespStatus() {
        return this.status;
    }

    public String getRespDesc() {
        return this.desc;
    }

    public Object getBody() {
        return this.body;
    }

//    public int getStatus() {
//        return this.status;
//    }
//
//    public String getDesc() {
//        return this.desc;
//    }
}
