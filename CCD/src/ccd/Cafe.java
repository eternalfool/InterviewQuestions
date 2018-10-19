package ccd;

import java.util.List;
import java.util.Optional;

/**
 * Created by shashwat on 23/04/17.
 */
public class Cafe {
    List<VendorMachine> vendorMachines;
    private Object vendorMachineLock = new Object();

    public Cafe(List<VendorMachine> vendorMachines) {
        this.vendorMachines = vendorMachines;
    }

    /*
    Following Method checks if any vendingMachine is free, if yes allocates one to customer.
    If None of the machines are free, it sleeps before trying again.
     */
    public VendorMachine getAvailibleVendorMachine(Customer customer) throws InterruptedException {
        synchronized (vendorMachineLock) {
            while (true) {
                Optional<VendorMachine> optionalVen = vendorMachines.stream().filter(v -> !v.isBusy).findAny();
                if (optionalVen.isPresent()) {
                    VendorMachine vendorMachine = optionalVen.get();
                    System.out.println("Customer: " + customer.hashCode() + " Got VendingMachine: " + vendorMachine.hashCode());
                    vendorMachine.isBusy = true;
                    return vendorMachine;
                } else {
                    System.out.println("Server Busy, Your order is important to us " + customer.hashCode());
                    Thread.sleep(1000);
                }
            }
        }
    }
}
