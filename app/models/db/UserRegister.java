package models.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserRegister {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userID")
    int id;

    @play.data.validation.Constraints.Required
    @Column(name = "userName")
    private String uNameR;

    @play.data.validation.Constraints.Required
    @Column(name = "password")
    private String pwordR;

    @play.data.validation.Constraints.Required
    @Column(name = "displayName")
    private String dNameR;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
