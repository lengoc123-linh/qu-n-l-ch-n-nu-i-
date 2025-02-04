package model;

import java.util.Objects;

public class Role {
    private String roleName;
    private String description;

    public Role() {
    }

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, description);
    }

    @Override
    public String toString() {
        return "Role{" + "roleName=" + roleName + ", description=" + description + '}';
    }
}
