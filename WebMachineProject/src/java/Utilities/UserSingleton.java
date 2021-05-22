package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pojos.User;


public class UserSingleton {
    
    private static ArrayList<User> users = new ArrayList<User>();
    
    public static ArrayList<User> getUsers() throws SQLException{
        getData();
        return users;
    }
    
    public static void getData() throws SQLException
    {
        Connection con = connection();
        if (con == null)
          {
            System.out.println("cannot connect to database");
          }
        try
          {
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sqlStr = "SELECT * from users";
            
            ps = con.prepareStatement(sqlStr);
            rs = ps.executeQuery();
            while (rs.next()){
                User user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
                users.add(user);
            }
          }
        finally
          {
            con.close();
          }
    }
    
    public static void addUser(String user, String pass, String email) throws SQLException{
        Connection con = connection();
        if (con == null)
          {
            System.out.println("cannot connect to database");
          }
        try
          {
            String userenc = DESUtil.encrypt(user);
            String passenc = DESUtil.encrypt(pass);
            String emenc = DESUtil.encrypt(email);
            String sqlStr = "INSERT INTO users (Username, Password, Email) VALUES ('" + userenc + "', '" + passenc + "', '" + emenc +"')";
            Statement stmnt;
            stmnt = con.createStatement();
            stmnt.execute(sqlStr);
          }
        finally
          {
            con.close();
          }
    }
    
    private static Connection connection() //throws InstantiationException, IllegalAccessException
    {
        String databaseName = "website";
        String userName = "root";
        String password = "";
        String URL2 = "com.mysql.jdbc.Driver";
        Connection con = null;
        try
          {// Load Sun's jdbc driver
            Class.forName(URL2).newInstance();
            System.out.println("JDBC Driver loaded!");
          }
        catch (Exception e) // driver not found
          {
            System.err.println("Unable to load database driver");
            System.err.println("Details : " + e);
            return null;
          }
        String ip = "localhost"; //internet connection
        String url = "jdbc:mysql://" + ip + ":3308/" + databaseName + "?characterEncoding=latin1";
        try
          {
            con = DriverManager.getConnection(url, userName, password);
            con.setReadOnly(false);
          }
        catch (Exception e)
          {
            System.err.println(e.toString());
            return null;
          }
        System.out.println("connection successfull");
        return con;
    }
}
