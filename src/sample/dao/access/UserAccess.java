package sample.dao.access;

import sample.dao.models.User;

import java.util.ArrayList;
import java.util.HashMap;

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
        Access.delete(new User().getTable(), id);
    }


    public static ArrayList getAll()
    {
       return Access.getAll(new User().getTable());
    }

    public static ArrayList search(HashMap<String, String> pattern){
        return Access.search(pattern,new User().getTable());
    }
}
