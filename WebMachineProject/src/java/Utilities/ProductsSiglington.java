package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pojos.Item;

public class ProductsSiglington {
    private static Map<Item, String> list = new HashMap<>();
    private static ProductsSiglington products;

    private ProductsSiglington()
    {
        listAll();
    }

    public static ProductsSiglington getProducts()
    {
        if (products == null)
        {
            products = new ProductsSiglington();
        }

        return products;
    }
    
    public Map<Item, String> getList()
    {
        return list;
    }

    private void listAll()
    {
        list.clear();
        Connection con = connection();
        if (con == null)
        {
            System.out.println("cannot connect to database");
            return;
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT  *  FROM inventory";
        try
        {
            ps = con.prepareStatement(sqlStr);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Item temp = new Item(rs.getInt(1), rs.getString(2), rs.getString(3));
                String link = temp.getName().toLowerCase().replace(' ', '_');
                link += ".xhtml";
                list.put(temp, link);
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        } finally
        {
            try
            {
                con.close();
                if (ps != null)
                {
                    ps.close();
                }
            } catch (SQLException sqle)
            {
                sqle.printStackTrace();
            }
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
