package sample.DAO;

import sample.Models.User;

import java.util.ArrayList;

public class UserAccess {

    public static void store(User user)
    {
        Access.store(user);
    }

    public static void update(User user, int id){

        Access.update(user, id);
    }

    public static void delete(int id)
    {
        Access.delete("users", id);
    }


    public static ArrayList getAll()
    {
       return Access.getAll("users");
    }
}
