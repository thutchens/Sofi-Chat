package models;

import play.data.validation.Constraints;

public class MessageHTML {

    @Constraints.Required(message = "No message was entered")
    private String msg;

    private String msgFrom;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(String msgFrom) {
        this.msgFrom = msgFrom;
    }

}
