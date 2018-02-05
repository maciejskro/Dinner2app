package pl.kayzone.dinnerapp.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(value="dinner", noClassnameStored = true)
public class Dinner implements Serializable {

    @Id
    private Integer idDinner;
    private Integer number;
    private String description;
    private BigDecimal price;
    private boolean isPayed;
    private User userID;


    public Dinner(Integer idDinner, Integer number, String description , BigDecimal price, boolean isPayed, User user ) {
        this( number, description, price,isPayed, user );
        this.idDinner = idDinner;
    }

    public Dinner(Integer number, String description, BigDecimal price, boolean isPayed, User user) {
        this.number = number;
        this.description = description;
        this.price = price;
        this.isPayed = isPayed;
        this.userID = user;
    }

    public Integer getIdDinner() {
        return idDinner;
    }

    public void setIdDinner(Integer idDinner) {
        this.idDinner = idDinner;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
