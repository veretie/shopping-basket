package uk.co.mits4u.basket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.co.mits4u.basket.api.ItemEntry;
import uk.co.mits4u.basket.api.PricingResult;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

@Service
public class BasketApiService {

    private static Logger logger = LoggerFactory.getLogger(BasketApiService.class);

    @Resource
    private BasketParser basketParser;
    @Resource
    private BasketPricer basketPricer;



    public PricingResult priceBasket(Collection<ItemEntry> basketEntries) {

        String correlationId = UUID.randomUUID().toString();
        logger.info("CID: " + correlationId + "; request: " + basketEntries);

        ShoppingBasket shoppingBasket = basketParser.apply(basketEntries);
        BigDecimal totalCost = basketPricer.totalCost(shoppingBasket);
        PricingResult pricingResult = new PricingResult(totalCost);

        logger.info("CID: " + correlationId + "; response: " + pricingResult);
        return pricingResult;

    }
}
