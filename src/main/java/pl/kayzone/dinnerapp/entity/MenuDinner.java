package pl.kayzone.dinnerapp.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.util.List;

@Entity(value = "menudinner", noClassnameStored = true)
public class MenuDinner extends BaseEntity implements Serializable {

    @Property("week")
    private Integer weekNumber;
    @Property("startdate")
    private LocalDateTime menudate;
    @Property("menuItems")
    private List<String> menuitem;

    public MenuDinner() {

    }

    public MenuDinner(LocalDateTime menudate, List<String> menuitem) {
        this();
        this.weekNumber = menudate.getYear() * 100 + menudate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        this.menudate = menudate;
        this.menuitem = menuitem;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public LocalDateTime getMenudate() {
        return menudate;
    }

    public void setMenudate(LocalDateTime menudate) {
        this.menudate = menudate;
    }

    public List<String> getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(List<String> menuitem) {
        this.menuitem = menuitem;
    }

    public String getWeekDayName() {
        switch (menudate.getDayOfWeek().getValue()) {
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
