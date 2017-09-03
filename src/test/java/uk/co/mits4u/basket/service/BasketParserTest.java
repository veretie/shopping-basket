package uk.co.mits4u.basket.service;

import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.mits4u.basket.api.ItemEntry;
import uk.co.mits4u.basket.api.ItemName;
import uk.co.mits4u.basket.data.ProductStore;
import uk.co.mits4u.basket.model.ShoppingBasketEntry;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static uk.co.mits4u.basket.data.Stubber.apples;
import static uk.co.mits4u.basket.data.Stubber.bananas;
import static uk.co.mits4u.basket.data.Stubber.itemEntry;

public class BasketParserTest {

    @InjectMocks
    private BasketParser basketParser;

    @Mock
    private ProductStore productStore;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(productStore.findProductByName(ItemName.Apples)).thenReturn(apples());
        when(productStore.findProductByName(ItemName.Bananas)).thenReturn(bananas());
    }

    @Test
    public void testParseShoppingEmptyItems() {
        ShoppingBasket shoppingBasket = basketParser.apply(Lists.newArrayList());
        assertThat(shoppingBasket.getEntries()).isEmpty();
    }

    @Test
    public void testParseShoppingNullItems() {
        ShoppingBasket shoppingBasket = basketParser.apply(null);
        assertThat(shoppingBasket.getEntries()).isEmpty();
    }

    @Test
    public void testParseShoppingItems() {

        Collection<ItemEntry> entryCollection = Lists.newArrayList(
                itemEntry(ItemName.Apples, 1),
                itemEntry(ItemName.Bananas, 2),
                itemEntry(ItemName.Apples, 3)
        );
        ShoppingBasket shoppingBasket = basketParser.apply(entryCollection);
        assertThat(shoppingBasket.getEntries())
                .haveAtLeastOne(new Condition<>((e) -> verify(e, ItemName.Apples, 1), "c1"))
                .haveAtLeastOne(new Condition<>((e) -> verify(e, ItemName.Apples, 3), "c2"))
                .haveAtLeastOne(new Condition<>((e) -> verify(e, ItemName.Bananas, 2), "c3")
                );

    }

    @Test
    public void testParseShoppingItemsWhenOneItemHasMissingName() {

        Collection<ItemEntry> entryCollection = Lists.newArrayList(
                itemEntry(ItemName.Bananas, 2),
                itemEntry(null, 1),
                itemEntry(ItemName.Apples, 3)
        );

        Assertions.assertThatThrownBy(() -> basketParser.apply(entryCollection))
                .isInstanceOf(NullPointerException.class)
                .hasStackTraceContaining("Cannot process basket entry with empty item name");

    }


    private boolean verify(ShoppingBasketEntry shoppingBasketEntry, ItemName expectedName, int expectedQuantity) {
        return (shoppingBasketEntry.getProduct().getItemName().equals(expectedName))
                && shoppingBasketEntry.getQuantity() == expectedQuantity;
    }

}