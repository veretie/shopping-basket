package uk.co.mits4u.basket.data;

import uk.co.mits4u.basket.api.ItemEntry;
import uk.co.mits4u.basket.api.ItemName;
import uk.co.mits4u.basket.model.Product;
import uk.co.mits4u.basket.model.ShoppingBasketEntry;
import uk.co.mits4u.basket.service.ShoppingBasket;

import java.math.BigDecimal;

public class Stubber {

    public static ShoppingBasket stubShoppingBasket() {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.addEntry(new ShoppingBasketEntry(apples(), 1));
        shoppingBasket.addEntry(new ShoppingBasketEntry(bananas(), 5));
        return shoppingBasket;
    }

    public static ItemEntry itemEntry(ItemName itemName, int quantity){
        ItemEntry itemEntry = new ItemEntry();
        itemEntry.setQuantity(quantity);
        itemEntry.setItemName(itemName);
        return itemEntry;
    }

    public static Product apples() {
        return new Product.ProductBuilder(ItemName.Apples)
                .setPrice(new BigDecimal("1"))
                .build();
    }

    public static Product bananas() {
        return new Product.ProductBuilder(ItemName.Bananas)
                .setPrice(new BigDecimal("0.9"))
                .build();
    }


}