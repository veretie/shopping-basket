package uk.co.mits4u.basket.service.helper;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyHelperTest {

    @Test
    public void testRoundZero(){
        MoneyHelper moneyHelper = new MoneyHelper();
        BigDecimal result = moneyHelper.round(new BigDecimal("0"));
        assertThat(result).isEqualByComparingTo("0.00");
    }

    @Test
    public void testRoundHigher(){
        MoneyHelper moneyHelper = new MoneyHelper();
        BigDecimal result = moneyHelper.round(new BigDecimal("0.495"));
        assertThat(result).isEqualByComparingTo("0.50");
    }

    @Test
    public void testRoundLower(){
        MoneyHelper moneyHelper = new MoneyHelper();
        BigDecimal result = moneyHelper.round(new BigDecimal("0.494"));
        assertThat(result).isEqualByComparingTo("0.49");
    }

    @Test
    public void testRoundLarge(){
        MoneyHelper moneyHelper = new MoneyHelper();
        BigDecimal result = moneyHelper.round(new BigDecimal(Integer.MAX_VALUE));
        assertThat(result).isEqualByComparingTo("2147483647.00");
    }

}