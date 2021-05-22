package Utilities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named(value = "menuView")
@ViewScoped
public class MenuView implements Serializable
{

    private MenuModel model;

    @PostConstruct
    public void init()
    {
        model = new DefaultMenuModel();

              //First submenu
        DefaultSubMenu firstSubmenu = 
                DefaultSubMenu.builder()
                .styleClass("menu")
                .label("Computers")
                .build();
                
                DefaultMenuItem item = new DefaultMenuItem("Computer 1");

                item.setId("Computer 1");
                item.setParam("param1", "Computer1");
                item.setCommand("#{itemsBean.menuProduct}");
                
        firstSubmenu.getElements().add(item);
        
                item = new DefaultMenuItem("Computer 2");
                item.setId("Computer 2");
                item.setParam("param1", "Computer2");
                item.setCommand("#{itemsBean.menuProduct}");
                        
                   
        firstSubmenu.getElements().add(item);

        model.getElements().add(firstSubmenu);

        //Second submenu
        DefaultSubMenu secondSubmenu = DefaultSubMenu.builder()
                .label("Phones")
                .styleClass("menu")
                .build();

            item = new DefaultMenuItem("Phone 1");
            item.setId("Phone 1");
            item.setParam("param1", "Phone1");
            item.setCommand("#{itemsBean.menuProduct}");
            
                    
        secondSubmenu.getElements().add(item);

        item = new DefaultMenuItem("Phone 2");
            item.setId("Phone 2");
            item.setParam("param1", "Phone2");
            item.setCommand("#{itemsBean.menuProduct}");
              
        secondSubmenu.getElements().add(item);

        model.getElements().add(secondSubmenu);
    }

    public MenuModel getModel()
    {
        return model;
    }

   
    public void update()
    {
        addMessage("Success data updated", "Data updated");
    }

    public void delete()
    {
        addMessage("Success data deleted", "Data deleted");
    }
 public void callJSF()
    {
        addMessage("Success data deleted", "Data deleted");
    }
    public void addMessage(String summary, String detail)
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                summary,
                detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }   
    
}

