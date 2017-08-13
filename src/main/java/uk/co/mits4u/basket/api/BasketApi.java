package uk.co.mits4u.basket.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import uk.co.mits4u.basket.service.BasketApiService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;

@RestController
public class BasketApi {

    @Resource
    private BasketApiService service;

    @ApiOperation(value = "Price a shopping basket")
    @PostMapping(path = "/basket/calculatePrice", consumes = "application/json", produces = "application/json")
    public PricingResult priceBasket(@ApiParam(value = "Shopping items", required = true)
                              @RequestBody @Valid Collection<ItemEntry> itemEntries) {

        return service.priceBasket(itemEntries);

    }

}

