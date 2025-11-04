package Sumurduc.Alexandru.Model;

public class Player {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Float bank;
    private Boolean privatef;

    public Player() {
    }



    public Player(Long id, String username, String email, String phone, String password, Float Bank, Boolean privatef) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.bank = Bank;
        this.privatef = privatef;
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
    public Float getBank() {return bank;}

    public void setBank(Float bank) {
        this.bank = bank;
    }

    public Boolean getPrivatef() {
        return privatef;
    }

    public void setPrivatef(Boolean privatef) {
        this.privatef = privatef;
    }

}
