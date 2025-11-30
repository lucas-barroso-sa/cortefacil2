package cortefacil.unifor.models.DTOs.user;

import cortefacil.unifor.models.entities.User;
import cortefacil.unifor.models.enuns.UserType;

public class UserInsertDTO {
    private Long id;
    private String name;
    private String username;
    private String password; // Em produção, evite retornar a senha no GET!
    private UserType Usertype;
    private Double commissionPercentage;

    public UserInsertDTO() {}

    public UserInsertDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.Usertype = entity.getType();
        this.commissionPercentage = entity.getCommissionPercentage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return Usertype;
    }

    public void setType(UserType type) {
        this.Usertype = type;
    }

    public Double getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }
}
