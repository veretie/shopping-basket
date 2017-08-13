package uk.co.mits4u.basket.service;

import org.springframework.stereotype.Component;
import uk.co.mits4u.basket.service.helper.MoneyHelper;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class BasketPricer {

    @Resource
    private MoneyHelper moneyHelper;

    public BigDecimal totalCost(ShoppingBasket shoppingBasket) {
        return moneyHelper.round(getTotalCost(shoppingBasket));
    }

    private BigDecimal getTotalCost(ShoppingBasket shoppingBasket) {
        return shoppingBasket.getEntries().parallelStream()
                .map(entry -> {
                    BigDecimal price = entry.getProduct().getPrice();
                    BigDecimal quantity = new BigDecimal(entry.getQuantity());
                    return price.multiply(quantity);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
