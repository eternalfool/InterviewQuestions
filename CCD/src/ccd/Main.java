package ccd;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    Say you have a Cafe having a lot of vending machines.
    And the vending machines have to serve a lot of customers.
    How do you model this in code?
    Asked in Amazon Interview
 */

public class Main {

    public static void main(String[] args) {
        VendorMachine v1 = new VendorMachine();
        VendorMachine v2 = new VendorMachine();
        VendorMachine v3 = new VendorMachine();
        VendorMachine v4 = new VendorMachine();
        VendorMachine v5 = new VendorMachine();
        List<VendorMachine> vendorMachines = Arrays.asList(v1, v2, v3, v4, v5);
        Cafe cafe = new Cafe(vendorMachines);
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
        Customer c1 = new Customer(cafe);
        Customer c2 = new Customer(cafe);
        Customer c3 = new Customer(cafe);
        Customer c4 = new Customer(cafe);
        Customer c5 = new Customer(cafe);
        Customer c6 = new Customer(cafe);
        Customer c7 = new Customer(cafe);
        Customer c8 = new Customer(cafe);
        Customer c9 = new Customer(cafe);
        Customer c10 = new Customer(cafe);
        Customer c11 = new Customer(cafe);
        executorService2.submit(c1);
        executorService2.submit(c2);
        executorService2.submit(c3);
        executorService2.submit(c4);
        executorService2.submit(c5);
        executorService2.submit(c6);
        executorService2.submit(c7);
        executorService2.submit(c8);
        executorService2.submit(c9);
        executorService2.submit(c10);
        executorService2.submit(c11);
        executorService2.shutdown();
    }
}
