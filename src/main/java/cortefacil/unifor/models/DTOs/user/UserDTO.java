package cortefacil.unifor.models.DTOs.user;

import cortefacil.unifor.models.entities.User;
import cortefacil.unifor.models.enuns.UserType;

public class UserDTO {
    private Long id;
    private String username;
    private UserType userType;
    private double commissionPercentage;
    private String name;

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.userType = entity.getType();
        this.commissionPercentage = entity.getCommissionPercentage();
        this.name = entity.getName();
    }
    public UserDTO() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
