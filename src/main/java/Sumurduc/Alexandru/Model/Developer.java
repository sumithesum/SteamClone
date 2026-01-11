package Sumurduc.Alexandru.Model;

import jakarta.validation.constraints.*;

public class Developer {

    private Long id;

    @NotBlank(message = "username is required")
    @Size(min = 3, max = 100)
    private String username;

    @Email(message = "email must be valid")
    private String email;

    @Size(max = 50)
    private String phone;

    @NotBlank(message = "password is required")
    @Size(min = 6, max = 255)
    private String password;

    @PositiveOrZero(message = "bank must be >= 0")
    private Float bank;

    @Size(max = 255)
    private String studioName;

    public Developer() {}

    public Developer(Long id, String username, String email, String phone,
                     String password, Float bank, String studioName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.bank = bank;
        this.studioName = studioName;
    }

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

    public String getStudioName() { return studioName; }
    public void setStudioName(String studioName) { this.studioName = studioName; }
}
