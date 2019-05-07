package sample.Models;

public class User {

    private String firstname;
    private  String lastname;
    private String birthday;
    private String cne;
    private String email;
    private String password;
    private String level;

    public String getFirstname() {
        return firstname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
