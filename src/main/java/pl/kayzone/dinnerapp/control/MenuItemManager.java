package pl.kayzone.dinnerapp.control;

import org.mongodb.morphia.query.Query;
import pl.kayzone.dinnerapp.entity.MenuDinner;

import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
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

    public void remove() {


    }

    public MenuDinner find(LocalDateTime startDate) {
        MenuDinner result = null;
        result = queryMenuDinner.field("week").equal(startDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)).get();
        return result;
    }

    public List<MenuDinner> findListMenu(LocalDateTime startDate) {
        ArrayList<MenuDinner> result = new ArrayList<>();
        queryMenuDinner.field("week");
        return result;
    }
}
