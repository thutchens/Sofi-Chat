package models;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Pattern;

public class UserRegisterHTML {

    @play.data.validation.Constraints.Required
    @MaxLength(10)
    @Pattern(value = "[\\S]+", message = "Cannot use spaces")
    private String uNameR;

    @play.data.validation.Constraints.Required
    @MaxLength(25)
    @Pattern(value = "[\\S]+", message = "Cannot use spaces")
    private String pwordR;

    @play.data.validation.Constraints.Required
    @MaxLength(10)
    @Pattern(value = "[\\S]+", message = "Cannot use spaces")
    private String dNameR;

    public String getuNameR() {
        return uNameR;
    }

    public void setuNameR(String uNameR) {
        this.uNameR = uNameR;
    }

    public String getPwordR() {
        return pwordR;
    }

    public void setPwordR(String pwordR) {
        this.pwordR = pwordR;
    }

    public String getdNameR() {
        return dNameR;
    }

    public void setdNameR(String dNameR) {
        this.dNameR = dNameR;
    }
}
