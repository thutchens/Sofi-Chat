package models;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Pattern;

public class UserLoginHTML {

    @play.data.validation.Constraints.Required
    @MaxLength(10)
    @Pattern(value = "[\\S]+", message = "Cannot use spaces")
    private String uName;

    @play.data.validation.Constraints.Required
    @MaxLength(10)
    @Pattern(value = "[\\S]+", message = "Cannot use spaces")
    private String pword;

    private String dName;

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }
}
