package dao;

import dao.access.UserAccess;
import dao.models.User;

public class Main  {

    public static void main(String[] args) {
        User user = new User("", "", "user", "1549688", "aubin.birba@gmail.cmo", "mypass", "2ite");
        UserAccess.store(user);
    }
}
