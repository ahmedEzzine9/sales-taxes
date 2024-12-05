package com.ahmed.itsf.salesTaxes.service;

import com.ahmed.itsf.salesTaxes.model.Item;
import com.ahmed.itsf.salesTaxes.model.dto.ItemRequest;
import com.ahmed.itsf.salesTaxes.model.dto.ReceiptResponse;
import com.ahmed.itsf.salesTaxes.service.serviceImp.DefaultReceiptGenerator;
import com.ahmed.itsf.salesTaxes.service.serviceImp.DefaultTaxCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class DefaultReceiptGeneratorTest {
    private DefaultReceiptGenerator receiptService;
    private DefaultTaxCalculator mockTaxCalculator;

    @BeforeEach
    public void setUp() {
        mockTaxCalculator = Mockito.mock(DefaultTaxCalculator.class);
        receiptService = new DefaultReceiptGenerator(mockTaxCalculator);
    }

    @Test
    public void testGenerateReceipt1() {
        List<ItemRequest> items = List.of(
                new ItemRequest("book", 12.49, false, true),
                new ItemRequest("music CD", 14.99, false, false),
                new ItemRequest("chocolate bar", 0.85, false, true)
        );

        // Mock  calculations
        Mockito.when(mockTaxCalculator.calculateTax(any(Item.class))).thenAnswer(invocation -> {
            Item item = invocation.getArgument(0);
            if (item.getName().equals("book")) return 0.00;
            if (item.getName().equals("music CD")) return 1.50;
            if (item.getName().equals("chocolate bar")) return 0.00;
            return 0.00;
        });


        ReceiptResponse response = receiptService.generateReceipt(items);

        // Assert Result

        assertEquals(12.49, response.getItems().get(0).getPrice(), 0.01); // book
        assertEquals(16.49, response.getItems().get(1).getPrice(), 0.01); // music CD
        assertEquals(0.85, response.getItems().get(2).getPrice(), 0.01); // chocolate bar
        assertEquals(29.83, response.getTotal(), 0.01);
        assertEquals(1.50, response.getSalesTaxes(), 0.01);
    }

    @Test
    public void testGenerateReceipt2() {
        // Input
        List<ItemRequest> items = List.of(
                new ItemRequest("imported box of chocolates", 10.00, true, true),
                new ItemRequest("imported bottle of perfume", 47.50, true, false)
        );

        // Mock  calculations
        Mockito.when(mockTaxCalculator.calculateTax(any(Item.class))).thenAnswer(invocation -> {
            Item item = invocation.getArgument(0);
            if (item.getName().equals("imported box of chocolates")) return 0.50;
            if (item.getName().equals("imported bottle of perfume")) return 7.15;
            return 0.00;
        });


        ReceiptResponse response = receiptService.generateReceipt(items);

        // Assert Result

        assertEquals(10.50, response.getItems().get(0).getPrice(), 0.01); // imported box of chocolates
        assertEquals(54.65, response.getItems().get(1).getPrice(), 0.01); // imported bottle of perfume
        assertEquals(65.15, response.getTotal(), 0.01);
        assertEquals(7.65, response.getSalesTaxes(), 0.01);
    }

    @Test
    public void testGenerateReceipt3() {
        // Input
        List<ItemRequest> items = List.of(
                new ItemRequest("imported bottle of perfume", 27.99, true, false),
                new ItemRequest("bottle of perfume", 18.99, false, false),
                new ItemRequest("packet of headache pills", 9.75, false, true),
                new ItemRequest("box of imported chocolates", 11.25, true, true)
        );

        // Mock  calculations
        Mockito.when(mockTaxCalculator.calculateTax(any(Item.class))).thenAnswer(invocation -> {
            Item item = invocation.getArgument(0);
            if (item.getName().equals("imported bottle of perfume")) return 4.20;
            if (item.getName().equals("bottle of perfume")) return 1.90;
            if (item.getName().equals("packet of headache pills")) return 0.00;
            if (item.getName().equals("box of imported chocolates")) return 0.60;
            return 0.00;
        });


        ReceiptResponse response = receiptService.generateReceipt(items);

        // Assert Result
        assertEquals(32.19, response.getItems().get(0).getPrice(), 0.01); // imported bottle of perfume
        assertEquals(20.89, response.getItems().get(1).getPrice(), 0.01); // bottle of perfume
        assertEquals(9.75, response.getItems().get(2).getPrice(), 0.01); // packet of headache pills
        assertEquals(11.85, response.getItems().get(3).getPrice(), 0.01); // box of imported chocolates

        assertEquals(74.68, response.getTotal(), 0.01);
        assertEquals(6.70, response.getSalesTaxes(), 0.01);
    }
}