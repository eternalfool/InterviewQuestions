package ccd;

/**
 * Created by shashwat on 23/04/17.
 */
public class Order {
    private BeverageType beverageType;
    private int volume;

    public Order(BeverageType beverageType, int volume) {
        this.beverageType = beverageType;
        this.volume = volume;
    }

    public BeverageType getBeverageType() {
        return beverageType;
    }

    public void setBeverageType(BeverageType beverageType) {
        this.beverageType = beverageType;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Order{" +
                "beverageType=" + beverageType +
                ", volume=" + volume +
                '}';
    }
}
