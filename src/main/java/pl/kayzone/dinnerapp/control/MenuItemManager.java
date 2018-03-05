package pl.kayzone.dinnerapp.control;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import pl.kayzone.dinnerapp.entity.MenuDinner;

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

    public void remove(MenuDinner menu) {
        Query<MenuDinner> remove = getDatastore().createQuery(MenuDinner.class)
                            .filter("_id", menu.getId());
        getDatastore().delete(remove);
    }

    public MenuDinner find(LocalDateTime startDate) {
        MenuDinner result = null;
        Query<MenuDinner> query = queryMenuDinner;
        query.filter("startdate >=",startDate );
        result = queryMenuDinner.get();
        return result;
    }

    public List<MenuDinner> findListMenu(LocalDateTime startDate) {
        ArrayList<MenuDinner> result = new ArrayList<>();
        Query<MenuDinner> query = queryMenuDinner;
        ZonedDateTime inputDate = ZonedDateTime.of(startDate,ZoneId.of("Europe/Warsaw"))
                    .truncatedTo(ChronoUnit.DAYS);
        query.criteria("startdate").greaterThanOrEq(inputDate)
                    .and(queryMenuDinner.criteria("week")
                            .equal(inputDate.getYear()*100+inputDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) ));
        result.addAll(queryMenuDinner.asList());
        return result;
    }
    public List<String> findWeekMenuDinner(LocalDateTime dateTime) {
        Query<MenuDinner> query = queryMenuDinner;
        List<String> result ;
        query.field("week").equal(dateTime.getYear()*100+dateTime.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        List<MenuDinner> templist = query.asList();
        result = templist.stream()
                 .map( md -> { List<String> alfaret = new ArrayList<>();
                         alfaret.add(md.getWeekDayName() + "-"
                                     +md.getMenudate().getDayOfMonth()+"."
                                     +md.getMenudate().getMonth().getValue());
                         alfaret.addAll(md.getMenuitem().keySet());
                     return alfaret;
                 })
                .flatMap(l-> l.stream())
                .collect(Collectors.toList());
        return result;
    }

    @SuppressWarnings("unused")
	public void updateMenuDinner(ObjectId id , MenuDinner mdid) {
        UpdateOperations<MenuDinner> ops = getDatastore().createUpdateOperations(MenuDinner.class).inc("version");
        Query<MenuDinner> updateQuery = queryMenuDinner.field("_id").equal(id);
       // getDatastore().update();

    }
}
