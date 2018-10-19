package ccd;

/**
 * Created by shashwat on 23/04/17.
 */
public class Customer implements Runnable {

    private final Cafe cafe;

    public Customer(Cafe cafe) {
        this.cafe = cafe;
    }

    @Override
    public void run() {
        try {
            VendorMachine ccd = cafe.getAvailibleVendorMachine(this);
            Order order = new Order(BeverageType.CAPPUCHINO, 23);
            ccd.processOrder(order);
            System.out.println("Customer: " + this.hashCode() + " has gotten her order");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
