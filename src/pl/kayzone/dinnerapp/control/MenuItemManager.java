package pl.kayzone.dinnerapp.control;

import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import pl.kayzone.dinnerapp.entity.MenuDinner;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuItemManager extends BaseManager {

    private List<MenuDinner> menuDinnerList;
    private Query<MenuDinner> queryMenuDinner;

    public MenuItemManager(String conn) {
        super(conn);
        this.menuDinnerList = new ArrayList<>();
        this.queryMenuDinner = super.getDatastore().createQuery(MenuDinner.class);
    }

    public void add(MenuDinner menu) {
        this.menuDinnerList.add(menu);
        super.save(menu);
        }

    public void add(List<MenuDinner> menus) {
        this.menuDinnerList.addAll(menus);
        super.save(menus);
        }

    public void remove(MenuDinner menuDinner) {
        this.queryMenuDinner.get();

    }
    public MenuDinner find (Date startDate) {
            MenuDinner result ;
            Query<MenuDinner> q = super.getDatastore().createQuery(MenuDinner.class);
            result = q.field("startdate").equal(startDate).get();
        return result;
    }

    public List<MenuDinner> findListMenu (Date startDate) {
        List<MenuDinner> result;
        Query<MenuDinner> q = super.getDatastore().createQuery(MenuDinner.class);
              result = q.field("startdate").greaterThanOrEq(startDate).asList() ;
        return result;
    }
}
