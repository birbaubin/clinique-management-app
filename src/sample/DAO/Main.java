package sample.DAO;

import sample.Models.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        UserAccess dao = new UserAccess();
        /*User user = new User();
        user.setEmail("aubin.birba@gmail.com");
        user.setPassword("mypass");
        user.setFirstname("aubin");
        user.setLastname("birba");
        dao.update(user, 5);*/
        ArrayList<HashMap<String , String>> result = dao.getAll();
        for(int i = 0; i < result.size(); i++)
        {
            System.out.println(result.get(i).get("email"));
        }
    }
}
