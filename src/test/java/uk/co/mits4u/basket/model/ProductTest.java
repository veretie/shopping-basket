package uk.co.mits4u.basket.model;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import uk.co.mits4u.basket.api.ItemName;

import java.math.BigDecimal;

public class ProductTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkProductCreation() {

        Product product = new Product.ProductBuilder(ItemName.Apples)
                .setPrice(new BigDecimal("1.0"))
                .build();

        Assertions.assertThat(product.getItemName()).isEqualTo(ItemName.Apples);
        Assertions.assertThat(product.getPrice()).isEqualTo(new BigDecimal("1.0"));

    }

    @Test
    public void checkProductCreationWhenProductIsNull() {

        Assertions.assertThatThrownBy(() -> new Product.ProductBuilder(null).build())
                .hasMessage("Item name is missing");

    }


    @Test
    public void testProductEquality() {

        Product p1 = new Product.ProductBuilder(ItemName.Apples)
                .setPrice(new BigDecimal("1.0"))
                .build();
        Product p2 = new Product.ProductBuilder(ItemName.Apples)
                .setPrice(new BigDecimal("0.8888"))
                .build();
        Product p3 = new Product.ProductBuilder(ItemName.Bananas)
                .setPrice(new BigDecimal("0.8888"))
                .build();

        Assertions.assertThat(p1).isEqualTo(p2);
        Assertions.assertThat(p1.equals(null)).isFalse();
        Assertions.assertThat(p1).isNotEqualTo(p3);

    }


    @Test
    public void testProductHash() {

        Product p1 = new Product.ProductBuilder(ItemName.Apples)
                .setPrice(new BigDecimal("1.0"))
                .build();
        Product p2 = new Product.ProductBuilder(ItemName.Apples)
                .setPrice(new BigDecimal("0.8888"))
                .build();

        Assertions.assertThat(p1.hashCode()).isEqualTo(p2.hashCode());

    }

}