package uk.co.mits4u.basket.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "Shopping basket pricing data")
public class PricingResult {

    @ApiModelProperty(value = "total cost of items")
    private BigDecimal totalCost;

    public PricingResult(@JsonProperty("totalPrice") BigDecimal totalPrice) {
        this.totalCost = totalPrice;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("totalCost", totalCost)
                .toString();
    }


}
