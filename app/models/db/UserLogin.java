package models.db;

import models.UserLoginHTML;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserLogin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userID")
    int id;

    @Column(name = "userName")
    private String uName;

    @Column(name = "password")
    private String pword;

    @Column(name = "displayName")
    private String dName;

    public UserLogin(UserLoginHTML user) {
        this.uName = user.getuName();
        this.pword = user.getPword();
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
