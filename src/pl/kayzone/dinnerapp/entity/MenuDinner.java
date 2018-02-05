package pl.kayzone.dinnerapp.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.io.Serializable;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

@Entity(value="menudinner", noClassnameStored = true
)
public class MenuDinner extends BaseEntity implements Serializable {

    @Property("week")
    private Integer weekNumber;
    @Property("startdate")
    private Date menudate;
    @Property("menuItems")
    private List<String> menuitem;

    public MenuDinner() {

    }

    public MenuDinner(Date menudate, List<String> menuitem) {
        this();
        Calendar cal = Calendar.getInstance();
        cal.setTime(menudate);
        this.weekNumber =cal.getWeekYear()*100 + cal.get(Calendar.WEEK_OF_YEAR);
        this.menudate = menudate;
        this.menuitem = menuitem;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Date getMenudate() {
        return menudate;
    }

    public void setMenudate(Date menudate) {
        this.menudate = menudate;
    }

    public List<String> getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(List<String> menuitem) {
        this.menuitem = menuitem;
    }
    public String getWeekDayName() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(menudate);
        cal.setFirstDayOfWeek(0);
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 0:
                return "Poniedziałek";
            case 1:
                return "Wtorek";
            case 2:
                return "Środa";
            case 4:
                return "Czwartek";
            case 5:
                return "Piątek";
            case 6:
                return "Sobota";
            default:
                return "Niedziela";
        }

    }

    @Override
    public String toString() {
        return "MenuDinner{" +
                "weekname='" + weekNumber + '\'' +
                ", menudate=" + menudate +
                ", menuitem=" + menuitem +
                '}';
    }
}
