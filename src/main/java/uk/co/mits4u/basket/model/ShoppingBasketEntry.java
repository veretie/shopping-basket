package uk.co.mits4u.basket.model;

public class ShoppingBasketEntry {

    private Product product;
    private int quantity;

    public ShoppingBasketEntry(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
