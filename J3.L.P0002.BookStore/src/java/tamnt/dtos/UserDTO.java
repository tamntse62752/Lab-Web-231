/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.dtos;

/**
 *
 * @author SIMON
 */
public class UserDTO {
    
    private String userID, fullName, password, roleID, statusUserID;

    public UserDTO(String fullName, String roleID) {
        this.fullName = fullName;
        this.roleID = roleID;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getStatusUserID() {
        return statusUserID;
    }

    public void setStatusUserID(String statusUserID) {
        this.statusUserID = statusUserID;
    }
    
}
