package cz.upce.fei.inptp.databasedependency.entity;

import org.springframework.stereotype.Component;

/**
 * Role entity
 */
//@Component
public class Role {
    
    private String section;
    private String access;
    private String modifier;

    public Role() {
    }

    public Role(String section, String access, String modifier) {
        this.section = section;
        this.access = access;
        this.modifier = modifier;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "Role{" + "section=" + section + ", access=" + access + ", modifier=" + modifier + '}';
    }
    
}
