package model;

import java.util.Objects;

public class UserRole {
    private String userName;
    private String roleName;

    public UserRole() {
    }

    public UserRole(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, roleName);
    }

    @Override
    public String toString() {
        return "UserRole{" + "userName=" + userName + ", roleName=" + roleName + '}';
    }
}
