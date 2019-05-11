package sample.Models;

import sample.DAO.Hash;

public class User extends Model {

    private String firstname;
    private  String lastname;
    private String birthday;
    private String cne;
    private String email;
    private String password;
    private String level;


    public User(){
        this.setArgs();
        this.setTable();
    }


    private void setArgs()
    {
        this.args = new String[]{"firstname", "lastname", "birthday", "cne", "email", "password", "level"};
    }

    private void setTable()
    {
        this.table = "users";
    }

    public String getFirstname() {
        return firstname;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String[] getAttributes()
    {
        String[] result = new String[]{firstname, lastname, birthday, cne, email, Hash.getSecurePassword(password), level};
        return result;
    }



}
