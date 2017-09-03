package uk.co.mits4u.basket.service;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.mits4u.basket.api.ItemEntry;
import uk.co.mits4u.basket.data.ProductStore;
import uk.co.mits4u.basket.model.Product;
import uk.co.mits4u.basket.model.ShoppingBasketEntry;

import java.util.Collection;

@Component
public class BasketParser implements Function<Collection<ItemEntry>, ShoppingBasket> {

    @Autowired
    private ProductStore productStore;

    @Override
    public ShoppingBasket apply(Collection<ItemEntry> itemEntries) {

        ShoppingBasket shoppingBasket = new ShoppingBasket();
        if (CollectionUtils.isEmpty(itemEntries)) {
            return shoppingBasket;
        }

        itemEntries.parallelStream()
                .map(itemEntry -> validate(itemEntry))
                .map(itemEntry -> transform(itemEntry))
                .forEach(basketEntry -> shoppingBasket.addEntry(basketEntry));

        return shoppingBasket;
    }

    private ShoppingBasketEntry transform(ItemEntry itemEntry) {
        Product product = productStore.findProductByName(itemEntry.getItemName());
        ShoppingBasketEntry shoppingBasketEntry = new ShoppingBasketEntry(product, itemEntry.getQuantity());
        return shoppingBasketEntry;
    }

    private ItemEntry validate(ItemEntry itemEntry) {
        Preconditions.checkNotNull(itemEntry.getItemName(), "Cannot process basket entry with empty item name");
        return itemEntry;
    }


}
