package pl.kayzone.dinnerapp.entity;


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.io.Serializable;
import java.util.Objects;

@Entity(value = "user", noClassnameStored = true)
public class User extends BaseEntity implements Serializable {

    @Property("name")
    private String userNickName;
    @Property("firstname")
    private String userFirstName;
    @Property("surname")
    private String userSurname;
    @Property("account")
    private String userAccount;
    @Property("isActive")
    private boolean isActive;

    public User() {
        super();
    }

    public User(String userNickName, String firstName, String userSurname, String userAccount) {
        super();
        this.userNickName = userNickName.trim();
        this.userFirstName = firstName.trim();
        this.userSurname = userSurname.trim();
        this.userAccount = userAccount.trim();
        this.id = new ObjectId();
        this.isActive = true;
    }

    public User(ObjectId id, String userNickName, String firstName, String userSurname, String userAccount, boolean isActive) {
        this(userNickName, firstName, userSurname, userAccount);
        this.id = id;
        this.isActive = isActive;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName.trim();
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount.trim();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "userNickName='" + userNickName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userAccout='" + userAccount + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(getUserNickName(), user.getUserNickName()) &&
                Objects.equals(getUserSurname(), user.getUserSurname()) &&
                Objects.equals(getUserAccount(), user.getUserAccount());
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, getUserNickName(), getUserSurname(), getUserAccount());
    }
}
