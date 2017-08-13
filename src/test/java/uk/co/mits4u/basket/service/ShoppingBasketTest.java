package uk.co.mits4u.basket.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.mockito.Mockito;
import org.junit.Test;
import uk.co.mits4u.basket.model.ShoppingBasketEntry;

public class ShoppingBasketTest {

    private ShoppingBasket shoppingBasket;

    @Before
    public void setUp(){
        shoppingBasket = new ShoppingBasket();
    }

    @Test
    public void testGetEntries() throws Exception {

        ShoppingBasketEntry e1 = Mockito.mock(ShoppingBasketEntry.class);
        ShoppingBasketEntry e2 = Mockito.mock(ShoppingBasketEntry.class);
        ShoppingBasketEntry e3 = Mockito.mock(ShoppingBasketEntry.class);

        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.addEntry(e1);
        shoppingBasket.addEntry(e2);
        shoppingBasket.addEntry(e3);

        Assertions.assertThat(shoppingBasket.getEntries()).containsExactly(e1, e2, e3);

    }

    @Test
    public void testAddNullEntry() throws Exception {

        Assertions.assertThatThrownBy(()->shoppingBasket.addEntry(null))
                .hasMessage("Cannot add null entry");

    }

}