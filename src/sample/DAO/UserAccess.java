package sample.DAO;

import sample.Models.User;

import java.util.ArrayList;

public class UserAccess{

    public void store(User user)
    {
        Access.store(user);
    }

    public void update(User user, int id){

        Access.update(user, id);
    }

    public static void delete(int id)
    {
        Access.delete("user", id);
    }


    public ArrayList getAll()
    {
       return Access.getAll("user");
    }
}
