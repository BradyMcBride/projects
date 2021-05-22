package Beans;


import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.primefaces.PrimeFaces;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;
import pojos.Item;

@Named(value = "itemsBean")
@SessionScoped
public class ItemsBean implements Serializable {

    Map<String, Item> items = new LinkedHashMap();
    private Item i;

    // Connects to the database and gets a specific item
    public ItemsBean() {
        Connection con = connection();
        if (con == null) {
            System.out.println("cannot connect to database");
            return;
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT  *  FROM inventory";
        try {
            ps = con.prepareStatement(sqlStr);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String type = rs.getString(2);
                String name = rs.getString(3);
                String des;

                switch (id) {
                    case 1:
                        des = "Laptop $250";
                        break;
                    case 2:
                        des = "Desktop $500";
                        break;
                    case 3:
                        des = "Phone 1 $200";
                        break;
                    default:
                        des = "Phone 2 $300";
                        break;
                }

                String img = "resources/images/thumbnails/" + name.toLowerCase().replace(' ', '_') + ".jpg";
                System.out.println(img);
                Item temp = new Item(id, type, name, des, img);
                this.items.put(name, temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        i = items.get("Computer1");
    }
    
    public Item getI() {
        return i;
    }

    public void setI(Item i) {
        this.i = i;
    }
    
    //Selects a new product when you click from the dropdown menu
    public void menuProduct(MenuActionEvent e) {
        MenuItem menuItem = e.getMenuItem();
        String param = menuItem.getParams().get("param1").get(0);

        Set<String> keys = this.items.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (param.equals(key)) {
                i = items.get(key);
                PrimeFaces.current().executeScript("location.reload();");
                return;
            }
        }   
    }

    // Opens the connection to the database
    public static Connection connection()
    {

        String databaseName = "website";
        String userName = "root";
        String password = "";
        String URL2 = "com.mysql.jdbc.Driver";
        Connection con = null;
        try
        {
            Class.forName(URL2).newInstance();
            System.out.println("JDBC Driver loaded!");
        } catch (Exception e)
        {
            System.err.println("Unable to load database driver");
            System.err.println("Details : " + e);
            return null;
        }
        String ip = "localhost";
        String url = "jdbc:mysql://" + ip + ":3308/" + databaseName + "?characterEncoding=latin1";
        try
        {
            con = DriverManager.getConnection(url, userName, password);
            con.setReadOnly(false);
        } catch (Exception e)
        {
            System.err.println(e.toString());
            return null;
        }
        System.out.println("connection successfull");
        return con;
    }
}
