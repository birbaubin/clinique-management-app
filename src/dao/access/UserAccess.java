package dao.access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dao.models.User;

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


    public static ObservableList getAll()
    {

        ArrayList<HashMap<String, String>> allUsers = Access.getAll(new User().getTable());
        ObservableList users = FXCollections.observableArrayList();
        for(HashMap user: allUsers) {
            users.add(new User((String) user.get("firstname"),
                    (String) (user.get("lastname")),
                    (String) user.get("birthday"),
                    (String) user.get("cne"),
                    (String) user.get("email"),
                    (String) user.get("password"),
                    (String) user.get("level")));
        }

        return users;
    }

    public static ArrayList search(HashMap<String, String> pattern){
        return Access.search(pattern,new User().getTable());
    }
}
