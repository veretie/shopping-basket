package uk.co.mits4u.basket.api;

import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Shopping basket entry")
public class ItemEntry {

    @ApiModelProperty(value = "item in the basket")
    private ItemName itemName;

    @ApiModelProperty(value = "item quantity", example = "1")
    private int quantity;

    public ItemName getItemName() {
        return itemName;
    }

    public void setItemName(ItemName itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("itemName", itemName)
                .add("quantity", quantity)
                .toString();
    }
}
