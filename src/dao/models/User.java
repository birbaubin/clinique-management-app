package dao.models;

import dao.access.Hash;

public class User extends Model {

    private String firstname;
    private  String lastname;
    private String userType;
    private String cne;
    private String email;
    private String password;
    private String level;


    public User(){
        this.setArgs();
        this.setTable();
        this.id = 0;
    }


    public User(String firstname, String lastname, String userType, String cne, String email, String password, String level) {

        this();
        this.firstname = firstname;
        this.lastname = lastname;
        this.userType = userType;
        this.cne = cne;
        this.email = email;
        this.password = password;
        this.level = level;
        this.id = 0;

    }

    public int getId() {
        return id;
    }

    public User(int id, String firstname, String lastname, String userType, String cne, String email, String password, String level) {

        this();
        this.firstname = firstname;
        this.lastname = lastname;
        this.userType = userType;
        this.cne = cne;
        this.email = email;
        this.password = password;
        this.level = level;
        this.id = id;

    }

    //see Model
    private void setArgs()
    {
        this.args = new String[]{"firstname", "lastname", "userType", "cne", "email", "password", "level"};
    }

    private void setTable()
    {
        this.table = "users";
    }

    public String getFirstname() {
        return firstname;
    }

    public String getBirthday() {
        return userType;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUserType(String birthday) {
        this.userType = userType;
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
        String[] result = new String[]{firstname, lastname, userType, cne, email, Hash.getSecurePassword(password), level};
        return result;
    }



}
