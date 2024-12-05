package com.ahmed.itsf.salesTaxes.service.serviceImp;

import com.ahmed.itsf.salesTaxes.model.Item;
import com.ahmed.itsf.salesTaxes.model.dto.ItemRequest;
import com.ahmed.itsf.salesTaxes.model.dto.ReceiptResponse;
import com.ahmed.itsf.salesTaxes.service.IReciept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultReceiptGenerator implements IReciept {
    private final DefaultTaxCalculator taxCalculator;

    @Autowired
    public DefaultReceiptGenerator(DefaultTaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    @Override
    public ReceiptResponse generateReceipt(List<ItemRequest> itemRequests) {
        List<Item> items = new ArrayList<>();
        double totalTaxes = 0.0;
        double totalPrice = 0.0;

        for (ItemRequest request : itemRequests) {
            Item item = new Item(request.getName(), request.getPrice(), request.isImported(), request.isExempt());
            double tax = taxCalculator.calculateTax(item);
            double finalPrice = item.getPrice() + tax;
            totalTaxes += tax;
            totalPrice += finalPrice;

            // Update the item's price to include taxes
            Item updatedItem = new Item(item.getName(), finalPrice, item.isImported(), item.isExempt());
            items.add(updatedItem);
        }

        return new ReceiptResponse(items, totalTaxes, totalPrice);
    }

}
