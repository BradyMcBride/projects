package Beans;

import Utilities.DESUtil;
import Utilities.UserSingleton;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import pojos.User;

@Named(value = "login")
@RequestScoped
public class Login {

    private String user;
    private String pass;
    private String email;
    private ArrayList<User> users;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    //Validates the user's information
    public String validate() throws SQLException {
        users = UserSingleton.getUsers();
        if (user.matches("^(?=\\D)(?=.*\\d).{8,}$") && pass.matches("^(?=\\D)(?=.*\\d).{8,}$")) {
            for (int i = 0; i < users.size(); i++) {
                if (user.equals(DESUtil.decrypt(users.get(i).getUser())) && pass.equals(DESUtil.decrypt(users.get(i).getPass()))) {
                    return "logged";
                } else {
                    return "register";
                }
            }
        } else
            message = "Username and password must be 8 characters, start with a letter, and have at least one number";
            return null;
    }

    //adds a new user.
    public String add() throws SQLException {
        UserSingleton.addUser(user, pass, email);
        return "logged";
    }
}
