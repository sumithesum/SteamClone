package Sumurduc.Alexandru.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Player {

    private Long id;

    @NotBlank(message = "username is required")
    @Size(min = 3, max = 100, message = "username must be 3..100 chars")
    private String username;

    @Email(message = "email must be valid")
    private String email;

    @Size(max = 50, message = "phone too long")
    private String phone;

    @NotBlank(message = "password is required")
    @Size(min = 6, max = 255, message = "password must be at least 6 chars")
    private String password;

    @PositiveOrZero(message = "bank must be >= 0")
    private Float bank;

    private Boolean privatef;

    public Player() {}

//    public Player(Long id, String username, String email, String phone, String password, Float bank, Boolean privatef) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.phone = phone;
//        this.password = password;
//        this.bank = bank;
//        this.privatef = privatef;
//    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Float getBank() { return bank; }
    public void setBank(Float bank) { this.bank = bank; }

    public Boolean getPrivatef() { return privatef; }
    public void setPrivatef(Boolean privatef) { this.privatef = privatef; }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='***'" +
                ", bank=" + bank +
                ", privatef=" + privatef +
                '}';
    }
}
