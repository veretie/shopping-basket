package uk.co.mits4u.basket.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import uk.co.mits4u.basket.api.ItemName;

import java.math.BigDecimal;

public final class Product {

    private ItemName itemName;
    private BigDecimal price;

    public ItemName getItemName() {
        return itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private Product(ProductBuilder productBuilder) {
        this.itemName = productBuilder.itemName;
        this.price = productBuilder.price;
    }

    public static class ProductBuilder {

        private ItemName itemName;
        private BigDecimal price;

        public ProductBuilder(ItemName itemName) {
            this.itemName = itemName;
        }

        public ProductBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Product build() {
            Preconditions.checkNotNull(itemName, "Item name is missing");
            Preconditions.checkNotNull(price, "Product totalCost is missing");
            return new Product(this);
        }

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.itemName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Product other = (Product) obj;
        return Objects.equal(this.itemName, other.itemName);
    }
}
