package uk.co.mits4u.basket.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.mits4u.basket.data.Stubber;
import uk.co.mits4u.basket.model.ShoppingBasketEntry;
import uk.co.mits4u.basket.service.helper.MoneyHelper;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

public class BasketPricerTest {

    @InjectMocks
    private BasketPricer basketPricer;
    @Mock
    private MoneyHelper moneyHelper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void price() throws Exception {

        when(moneyHelper.round(new BigDecimal("32.7"))).thenReturn(new BigDecimal("32.70"));

        ShoppingBasket shoppingBasket = new ShoppingBasket();
        shoppingBasket.addEntry(new ShoppingBasketEntry(Stubber.apples(), 10));
        shoppingBasket.addEntry(new ShoppingBasketEntry(Stubber.apples(), 20));
        shoppingBasket.addEntry(new ShoppingBasketEntry(Stubber.bananas(), 1));
        shoppingBasket.addEntry(new ShoppingBasketEntry(Stubber.bananas(), 1));
        shoppingBasket.addEntry(new ShoppingBasketEntry(Stubber.bananas(), 1));

        BigDecimal totalCost = basketPricer.totalCost(shoppingBasket);
        Assertions.assertThat(totalCost).isEqualTo(new BigDecimal("32.70"));

    }

}