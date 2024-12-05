package com.ahmed.itsf.salesTaxes.service;

import com.ahmed.itsf.salesTaxes.model.Item;
import com.ahmed.itsf.salesTaxes.service.serviceImp.DefaultTaxCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultTaxCalculatorTest {
    private final DefaultTaxCalculator calculator = new DefaultTaxCalculator();

    @Test
    public void testCalculateTaxForExemptAndNonImportedItem() {
        Item item = new Item("1 book", 12.49, false, true);

        assertEquals(0.0, calculator.calculateTax(item));
    }

    @Test
    public void testCalculateTaxForNonExemptImportedItem() {
        Item item = new Item("1 imported bottle of perfume", 47.50, true, false);

        assertEquals(7.15, calculator.calculateTax(item));
    }

    @Test
    public void testCalculateTaxForNonExemptNonImportedItem() {
        Item item = new Item("2 bottle of perfume", 18.99, false, false);

        assertEquals(3.8, calculator.calculateTax(item));
    }

}
