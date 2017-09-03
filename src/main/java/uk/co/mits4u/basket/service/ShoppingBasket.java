package uk.co.mits4u.basket.service;

import com.google.common.base.Preconditions;
import uk.co.mits4u.basket.model.ShoppingBasketEntry;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.LinkedList;

public class ShoppingBasket {

    private Collection<ShoppingBasketEntry> shoppingBasketEntries;

    public ShoppingBasket() {
        this.shoppingBasketEntries = new LinkedList<>();
    }

    public synchronized void addEntry(@NotNull ShoppingBasketEntry shoppingBasketEntry) {
        Preconditions.checkNotNull(shoppingBasketEntry, "Cannot add null entry");
        shoppingBasketEntries.add(shoppingBasketEntry);
    }

    public synchronized Collection<ShoppingBasketEntry> getEntries() {
        return shoppingBasketEntries;
    }

}
