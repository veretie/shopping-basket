package uk.co.mits4u.basket.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class ItemEntryTest {

    @Test
    public void testToString(){
        ItemEntry itemEntry = new ItemEntry();
        itemEntry.setItemName(ItemName.Apples);
        itemEntry.setQuantity(1);
        Assertions.assertThat(itemEntry.toString()).isEqualTo("ItemEntry{itemName=Apples, quantity=1}");
    }

}