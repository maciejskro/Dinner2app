package pl.kayzone.dinnerapp.entity;

public enum MenuType {

    ZUPA(4.0),
    DRUGIE(13.0),
    DODATEK(13.0),
    NAPOJ(1.0);

    private double price;
    MenuType() {
    }

    MenuType(double price) {
        this.price= price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
