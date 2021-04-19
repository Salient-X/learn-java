public class Fruit {

    private String name;
    private String product;
    private double price;


    Fruit(String name, String product, double price) {
        this.name = name;
        this.product = product;
        this.price = price;

    }

    public String getName() {
        return this.name;
    }

    public String getProduct() {
        return this.product;
    }

    public double getPrice() {
        return this.price;
    }




}

// dont know how to do child class

public class Apple extends Fruit {

    Apple(String name, String product, double price) {
        super(name, product, price);
    }
    
}
