package pojos;

import java.util.ArrayList;

public class Product
{
    
    private String id;
    
    private String type;
    
    private int width;
    
    private int height;
    
    private int qty;
    
    private double price;
    
    private ArrayList<Byte> image;
    
    private String fakeImage = "";
    
    
     public String getFakeImage()  { return this.fakeImage;}

    public ArrayList<Byte> getImage()
    {
        return image;
    }
  

    public void setImage(ArrayList<Byte> image)
    {
        this.image = image;
    }
    public void setFakeImage(String fakeImage)
    {
        this.fakeImage = fakeImage;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    
    private String description;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }


    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
    }

    

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }


  
    public int getWidth()
    {
        return width;
    }


    public void setWidth(int width)
    {
        this.width = width;
    }


    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    
}
