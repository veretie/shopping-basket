package uk.co.mits4u.basket.data;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import uk.co.mits4u.basket.api.ItemName;
import uk.co.mits4u.basket.model.Product;

import java.math.BigDecimal;

public class ProductStoreTest {

    private ProductStore productStore;

    @Before
    public void setUp() {
        productStore = new ProductStore();
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testFindExistingProduct() {
        productStore.simulateDataFromDb();
        Product product = productStore.findProductByName(ItemName.Apples);
        Assertions.assertThat(product.getItemName()).isEqualTo(ItemName.Apples);
        Assertions.assertThat(product.getPrice()).isEqualByComparingTo(new BigDecimal("1"));
    }

    @Test
    public void testFindMissingProduct() {
        Assertions.assertThatThrownBy(() -> productStore.findProductByName(ItemName.Lemons))
                .hasMessage("Product 'Lemons' is not found");
    }

    @Test
    public void testFindNullProduct() {
        Assertions.assertThatThrownBy(() -> productStore.findProductByName(null))
                .hasMessage("Cannot search for null item");
    }


}