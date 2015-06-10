package models.db;

import models.UserRegisterHTML;

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

    @Column(name = "userName")
    private String uNameR;

    @Column(name = "password")
    private String pwordR;

    @Column(name = "displayName")
    private String dNameR;

    public UserRegister(UserRegisterHTML user) {
        this.uNameR = user.getuNameR();
        this.pwordR = user.getPwordR();
        this.dNameR = user.getdNameR();
    }

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
