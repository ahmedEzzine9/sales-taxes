package com.ahmed.itsf.salesTaxes.service;

import com.ahmed.itsf.salesTaxes.model.Item;


public interface ITaxCalculator {
    /**
     * calculate taxe for item
      * @param item
     * @return
     */
    double calculateTax(Item item);

    }
