package com.ahmed.itsf.salesTaxes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String name;
    private double price;
    private boolean isImported;
    private boolean isExempt;


    @Override
    public String toString() {
        return "Item{" + "name='" + name + '\'' + ", price=" + price + ", isImported=" + isImported + ", isExempt=" + isExempt + '}';
    }
}
