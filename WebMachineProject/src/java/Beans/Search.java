package Beans;
import Utilities.ProductsSiglington;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import pojos.Item;

@Named(value = "searches")
@SessionScoped
public class Search implements Serializable
{
    private String search;
    private String result;
    private Map<Item, String> list = ProductsSiglington.getProducts().getList();
    private Item[] items = list.keySet().toArray(new Item[list.size()]);
    private String[] links = list.values().toArray(new String[list.size()]);

    public String getSearch()
    {
        return search;
    }

    public void setSearch(String search)
    {
        this.search = search;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }
    
    public void search()
    {
        String s = "";
        if (search.equals(""))
        {
            s = "";
        }
        else if (search.equalsIgnoreCase("Computer") || "Computer".contains(search) || "computer".contains(search) || regexCheck(search, "computer"))
        {
            for (int i = 0; i < items.length; ++i)
                if (items[i].getType().equalsIgnoreCase("Computer"))
                    s += getLink(items[i].getName(), links[i]);
        }
        else if (search.equalsIgnoreCase("Phone") || "Phone".contains(search) || "phone".contains(search) || regexCheck(search, "phone"))
        {
            for (int i = 0; i < items.length; ++i)
                if (items[i].getType().equalsIgnoreCase("Phone"))
                    s += getLink(items[i].getName(), links[i]);
        }
        else if (search.equalsIgnoreCase("Laptop") || "Laptop".contains(search) || "laptop".contains(search) || regexCheck(search, "laptop"))
        {
            for (int i = 0; i < items.length; ++i)
                if (items[i].getType().equalsIgnoreCase("Laptop"))
                    s += getLink(items[i].getName(), links[i]);
        }
        else
        {
            for (int i = 0; i < items.length; ++i)
            {
                if (search.toLowerCase().contains(items[i].getName().toLowerCase()) || items[i].getName().toLowerCase().contains(search.toLowerCase()) || regexCheck(search, items[i].getName().toLowerCase()))
                    s += getLink(items[i].getName(), links[i]);
            }
        }
        result = s;
    }
    
    private String getLink(String name, String link)
    {
        return "<img src=\"resources/images/thumbnails/" + name.toLowerCase().replace(' ', '_') + ".jpg\" width=\"100p\" height=\"100p\"/><br/>"
                + "<a href=\"" + link + "\">" + name + "</a><br/>";
    }
    
    private boolean regexCheck(String search, String itemName)
    {
        String[] keywords = itemName.split(" ");
        for (String str: keywords)
        {
            StringBuilder s = new StringBuilder(str);
            for (int i = 0; i < str.length(); ++i)
            {
                if (i > 0)
                    s.replace((i - 1), (i +1), String.valueOf(str.charAt(i - 1)));
                s.replace(i, (i + 1), ".*");
                System.out.println(s);
                if (search.matches(s.toString()))
                    return true;
            }
        }
        return false;
    }
}