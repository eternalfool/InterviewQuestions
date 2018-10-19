package ccd;

/**
 * Created by shashwat on 23/04/17.
 */
public class Beverage {
    BeverageType beverageType;
    int volume;

    public Beverage(BeverageType beverageType, int volume) {
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
        return "Beverage{" +
                "beverageType=" + beverageType +
                ", volume=" + volume +
                '}';
    }
}
