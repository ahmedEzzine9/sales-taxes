package com.ahmed.itsf.salesTaxes.model.dto;

import com.ahmed.itsf.salesTaxes.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptResponse {
    private List<Item> items;
    private double salesTaxes;
    private double total;

    @Override
    public String toString() {
        return "ReceiptResponse{" + "items=" + items.stream().map(Item::toString).collect(Collectors.joining(" | ")) + ", salesTaxes=" + salesTaxes + ", total=" + total + '}';
    }

// Getters and Setters
}