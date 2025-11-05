package Sumurduc.Alexandru.Model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Developer {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Float bank;
    private String studio_name;

   public Developer(){}

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", bank=" + bank +
                ", studio_name='" + studio_name + '\'' +
                '}';
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getBank() {
        return bank;
    }

    public void setBank(Float bank) {
        this.bank = bank;
    }

    public String getStudio_name() {
        return studio_name;
    }

    public void setStudio_name(String studio_name) {
        this.studio_name = studio_name;
    }
}
