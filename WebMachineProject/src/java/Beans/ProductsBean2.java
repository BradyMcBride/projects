package Beans;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;
import pojos.Product;
import Utilities.SizeImage;
import Utilities.SizeImage;

@Named(value = "productsBean2")
@ApplicationScoped
public class ProductsBean2 {
String selectedProductFromParm = "sumsungS";
    Map<String, Product> products = new LinkedHashMap();
    private Product product;

    public String getSelectedProductFromParm() {
        return selectedProductFromParm;
    }

    public void setSelectedProductFromParm(String selectedProductFromParm) {
        this.selectedProductFromParm = selectedProductFromParm;
    }
    
    
    
    public Product getProduct()
    {
        return products.get(selectedProductFromParm);
    }
    
    public Product getProduct(String product)
    {
        return products.get(product);
    }
    
    public void next()
    {
        
        selectProduct();
    }
    
    public String getNextProduct()
    {
        Set<Map.Entry<String, Product>> set = products.entrySet();
        Iterator<Map.Entry<String, Product>>   it = set.iterator();
        
        int i = 0;
        String firstkey = "";
        while( it.hasNext() )
          {
           String key = it.next().getKey(); 
            if ( i++ == 0 )//if first the save it
                    firstkey = key;    
            if ( key.equals(selectedProductFromParm) )                  
                if ( it.hasNext() )
                  {
                    selectedProductFromParm = it.next().getKey(); 
                    break;
                  }
                
                else
                  {
                    selectedProductFromParm = firstkey;
                    break;
                  }
 
            
          }
        
       return selectedProductFromParm;               
    }
    
    
    public void command(){
        selectedProductFromParm = "iPhone11";
        selectProduct();
    }
    
     
    
    public Product getPrevProduct()
    {
        return products.get(selectedProductFromParm);
    }
    
    public ProductsBean2()
    {
        //WarehouseSingleton ws = InitializationBean.getWarehouse();
        //ProductsForSale<String, Keyable>  p = ws.getProducts();
      
        //System.out.println( " Warehouse in bean: " + InitializationBean.getWarehouse() );
        this.product = new Product();
        
        product.setId("iPhone11");
        product.setDescription("<b>iPhone 11 Technical Specifications </b> <br/>"
                + "Finish. Black, Green, Yellow, Purple, , White.<br/> "
                + "Capacity 1 64GB. ...Size and Weight 2 Width: ...Display. <br/>"
                + "Liquid Retina HD display. ..."
                + "<br/> "
                + "mater, and Dust Resistant 3 Rated IP68 <br/>"
                + "(maximum depth of 2 meters up to 30 minutes) <br/>"
                + "under IEC standard 60529."
                + "Chip. A13 Bionic chip. ...Camera. <br/>"
                + "Dual 12MP Ultra Wide and <br/> Wide cameras. ...<br/>"
                + "Video Recording."
        );
        product.setHeight(479);
        product.setWidth(298);
        product.setQty(10);
        product.setPrice(499.95);
        product.setType(" iPHONE 11");
        product.setFakeImage("resources/images/site/iPhone11.png");

        this.products.put(product.getId(), product);

        this.product = new Product();
        product.setId("sumsungS");
        product.setDescription("<b>S Series</b> </br>"
                + "If it's innovative, the Galaxy S Series has it in spades. <br/>"
                + "  Superior cameras. <br/> "
                + "A battery that charges other batteries. An expansive display <br/>"
                + "that clears the way for your creative vision.");
        product.setHeight(338);
        product.setWidth(426);
        product.setQty(20);
        product.setPrice(688.95);
        product.setType(" Sumsung Galaxy S");
        product.setFakeImage("resources/images/site/sumsung.jpg");
        this.products.put(product.getId(), product);
    }
    
    public String productDescription()
    {
        return "This is a product for sale. <br/> "
                + "It is a good product<br/>"
                + "Please buy it<br/>"
                + "<b>Thanks!</b>";
    }
    
    /**
     * Selects a product that comes from a FacesContext-parameter.
     * Sets the instance-var selectedProductFromParm to the Product.
     */
    public void selectProduct()
    {
        SizeImage si = new SizeImage();
        try
          {
        si.writeFile();
          }
        catch( Exception e){
            System.out.println(e.getMessage());
        }
            //>get FacesContext current instance.
        FacesContext context = FacesContext.getCurrentInstance();
            
            //> get params from FacesContext and put them in a Map
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
             
            //>Look for the parameter in your products
            //that come from the datanase
        Set<String> keys = this.products.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext())
          {
            String key = it.next();
                    //>>if the parameter in the products finds
                    //a match then, the instance var is assigned to product.
            if (params.get(key) != null)
                this.selectedProductFromParm = key;
          }
    }
    
    public void menuProduct(MenuActionEvent e){
        MenuItem menuItem = e.getMenuItem();
        String param = menuItem.getParams().get("param1").get(0);
        
        Set<String> keys = this.products.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext())
          {
              System.out.println("this is the param: " + param);
            String key = it.next();
              System.out.println("this is the key: " + key);
            if(param.equals(key)){
                System.out.println("selected before: "+ selectedProductFromParm);
                this.selectedProductFromParm = key;
                System.out.println("selected after: "+selectedProductFromParm);
                product = products.get(key);
                break;
            }
          }
        
    }
    
}
