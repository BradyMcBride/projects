package Utilities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named(value = "menuBar")
@ViewScoped
public class MenuBar implements Serializable
{
    private MenuModel model;
            

    @PostConstruct
    public void init()
    {
        model = new DefaultMenuModel();
        
        DefaultSubMenu theSubMenu = DefaultSubMenu.builder()
                .styleClass("menu")
                .label("Services")
                .build();
        
        DefaultMenuItem servs = DefaultMenuItem.builder()
                .value("Services")
                .styleClass("menu")
                .url("Services.xhtml")
                .build();
        
        theSubMenu.getElements().add(servs);
        model.getElements().add(theSubMenu);
        
        
        DefaultSubMenu secMenu = DefaultSubMenu.builder()
                .styleClass("menu")
                .label("About Us")
                .build();
        
        DefaultMenuItem menuItem = DefaultMenuItem.builder()
                .value("About Us")
                .styleClass("menu")
                .url("About.xhtml")
                .build();
        
        secMenu.getElements().add(menuItem);
        model.getElements().add(secMenu);
        
        DefaultSubMenu thirdMenu = DefaultSubMenu.builder()
                .styleClass("menu")
                .label("Contact Us")
                .build();
        
        menuItem = DefaultMenuItem.builder()
                .value("Contact Us")
                .styleClass("menu")
                .url("Contact.xhtml")
                .build();
        
        thirdMenu.getElements().add(menuItem);
        model.getElements().add(thirdMenu);
       
    }
    

    public MenuModel getModel()
    {
        return model;
    }

}

