package ccd;

/**
 * Created by shashwat on 23/04/17.
 */
public class VendorMachine {
    boolean isBusy = false;

    public Beverage processOrder(Order order) throws InterruptedException {
        Beverage beverage = prepareOrder(order);
        isBusy = false;
        return beverage;
    }

    private Beverage prepareOrder(Order order) throws InterruptedException {
        System.out.println("Preparing order: " + this.hashCode());
        Thread.sleep(5000);

        return new Beverage(order.getBeverageType(), order.getVolume());
    }
}
