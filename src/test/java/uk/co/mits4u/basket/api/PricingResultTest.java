package uk.co.mits4u.basket.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

public class PricingResultTest {

    @Test
    public void testToString() {
        PricingResult pricingResult = new PricingResult(new BigDecimal("1.0"));
        Assertions.assertThat(pricingResult.toString()).isEqualTo("PricingResult{totalCost=1.0}");
    }

}