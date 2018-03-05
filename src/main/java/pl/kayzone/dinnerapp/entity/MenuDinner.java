package pl.kayzone.dinnerapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "menudinner", noClassnameStored = true)
public class MenuDinner extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7364834664863008971L;
	@Property("week")
    private Integer weekNumber;
    @Property("startdate")
    private LocalDateTime menudate;
    @Property("menuItems")
    private HashMap<String, Double> menuitem;

    public MenuDinner() {
    		super();
    }

    public MenuDinner(LocalDateTime menudate, HashMap<String, Double> menuitem) {
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

    public HashMap<String,Double> getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(HashMap<String,Double> menuitem) {
        this.menuitem = menuitem;
    }

    public String getWeekDayName() {
        switch (menudate.getDayOfWeek()) {
            case MONDAY:
                return "Poniedziałek";
            case TUESDAY:
                return "Wtorek";
            case WEDNESDAY:
                return "Środa";
            case THURSDAY:
                return "Czwartek";
            case FRIDAY:
                return "Piątek";
            case SATURDAY:
                return "Sobota";
            default:
                return "Niedziela";
        }

    }

    @Override
    public String toString() {
        return "MenuDinner{" +
                "weeknumber='" + getWeekNumber() + '\'' +
                ", menudate=" + menudate +
                ", dayname=" + getWeekDayName() +
                ", menuitem=" + menuitem +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuDinner)) return false;
        MenuDinner that = (MenuDinner) o;
        return Objects.equals(getWeekNumber(), that.getWeekNumber()) &&
                Objects.equals(getMenudate(), that.getMenudate()) &&
                Objects.equals(getMenuitem(), that.getMenuitem());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getWeekNumber(), getMenudate(), getMenuitem());
    }
}
