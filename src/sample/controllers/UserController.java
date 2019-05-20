package sample.controllers;
import sample.dao.access.UserAccess;
import sample.dao.models.User;
import java.util.ArrayList;
import java.util.HashMap;

public class UserController {

    public static void addUser(User user)
    {
        try{
            Validator.validateForAddUser(user);
            HashMap<String, String> pattern = new HashMap<>();
            pattern.put("cne", user.getCne());
            ArrayList list = UserAccess.search(pattern);
            if(!list.isEmpty())
                throw new Exception("Un utilisateur avec ce cne existe déja.");
            pattern.clear();
            pattern.put("email", user.getEmail());
            list = UserAccess.search(pattern);
            if(!list.isEmpty())
                throw new Exception("Un utilisateur avec cet email existe déja.");
            UserAccess.store(user);
        }
        catch(Exception  e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void updateUser(User user, int id)
    {
        try{
            Validator.validateForAddUser(user);
            HashMap<String, String> pattern = new HashMap<>();
            pattern.put("cne", user.getCne());
            ArrayList<HashMap<String, String>> list = UserAccess.search(pattern);

            //verify that there is not another user that has the same cne
            if(!list.isEmpty())
            {
                if(!(list.get(0).get("id").equals(id)))
                    throw new Exception("Un autre utilisateur possède déja ce cne");
            }

            //verify that there is not another user that has same email
            pattern.clear();
            pattern.put("email", user.getEmail());
            list = UserAccess.search(pattern);
            if(!list.isEmpty())
            {
                if(!(list.get(0).get("id").equals(id)))
                    throw new Exception("Un autre utilisateur possède déja ce email");
            }

            UserAccess.update(user, id);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static User getUserInfos(int id)
    {
        HashMap<String, String> pattern = new HashMap<>();
        pattern.put("id", String.valueOf(id));
        ArrayList users = UserAccess.search(pattern);
        User foundUser = null;
        try{

            HashMap<String, String> user = (HashMap)(users.get(0));
            foundUser = new User();
            foundUser.setFirstname(user.get("firstname"));
            foundUser.setBirthday(user.get("birthday"));
            foundUser.setLastname(user.get("lastname"));
            foundUser.setLevel(user.get("level"));
            foundUser.setEmail(user.get("email"));
            foundUser.setCne(user.get("cne"));

            return foundUser;
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Il n'ya pas d'utilisateur avec cet id");
        }
        return foundUser;
    }


}
