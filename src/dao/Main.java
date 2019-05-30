package dao;

import dao.access.UserAccess;
import dao.models.User;

public class Main  {

    public static void main(String[] args) {


        User admin_1 = new User("Akram", "Aznakour", "admin", "G123456789", "akram.aznakour@gmail.com", "password", "2ite");
        User admin_2 = new User("Aubin", "Birba", "user", "1549688", "aubin.birba@gmail.cmo", "password", "2ite");

        UserAccess.store(admin_1);
        UserAccess.store(admin_2);

    }
}
