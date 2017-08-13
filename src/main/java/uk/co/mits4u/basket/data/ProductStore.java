package uk.co.mits4u.basket.data;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import uk.co.mits4u.basket.api.ItemName;
import uk.co.mits4u.basket.model.Product;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static uk.co.mits4u.basket.api.ItemName.*;

@Component
public class ProductStore {

    private Map<ItemName, Product> products;

    public ProductStore() {
        products = new ConcurrentHashMap<>();
    }

    @PostConstruct
    public void simulateDataFromDb() {
        stubData();
    }

    public Product findProductByName(@NotNull ItemName itemName) {
        Preconditions.checkNotNull(itemName, "Cannot search for null item");
        if (!products.containsKey(itemName)) {
            throw new IllegalArgumentException("Product '" + itemName + "' is not found");
        }
        return products.get(itemName);
    }

    private void stubData() {

        products.put(Apples, new Product.ProductBuilder(Apples)
                .setPrice(BigDecimal.ONE)
                .build()
        );
        products.put(Bananas, new Product.ProductBuilder(Bananas)
                .setPrice(new BigDecimal("0.9"))
                .build()
        );
        products.put(Lemons, new Product.ProductBuilder(Lemons)
                .setPrice(new BigDecimal("0.8"))
                .build()
        );
        products.put(Oranges, new Product.ProductBuilder(Oranges)
                .setPrice(new BigDecimal("0.7"))
                .build()
        );
        products.put(Peaches, new Product.ProductBuilder(Peaches)
                .setPrice(new BigDecimal("0.6"))
                .build()
        );

    }

}
