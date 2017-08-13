package uk.co.mits4u.basket.service;


import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import uk.co.mits4u.basket.api.ItemEntry;
import uk.co.mits4u.basket.api.PricingResult;
import uk.co.mits4u.basket.service.helper.MoneyHelper;

import java.math.BigDecimal;
import java.util.Collection;

public class BasketApiServiceTest {

    @InjectMocks
    private BasketApiService basketApiService;

    @Mock
    private BasketParser basketParser;
    @Mock
    private BasketPricer basketPricer;
    @Mock
    private MoneyHelper moneyHelper;
    @Mock
    private PricingResult pricingResult;
    @Mock
    private ShoppingBasket shoppingBasket;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPriceBasket(){

        Collection<ItemEntry> basketEntries = Lists.newArrayList(Mockito.mock(ItemEntry.class));
        Mockito.when(basketParser.apply(basketEntries)).thenReturn(shoppingBasket);
        Mockito.when(basketPricer.totalCost(shoppingBasket)).thenReturn(new BigDecimal("100.16"));

        PricingResult pricingResult = basketApiService.priceBasket(basketEntries);

        Assertions.assertThat(pricingResult.getTotalCost()).isEqualTo("100.16");

    }


}