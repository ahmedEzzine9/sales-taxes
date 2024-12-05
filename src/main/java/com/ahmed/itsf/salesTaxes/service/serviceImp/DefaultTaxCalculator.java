package com.ahmed.itsf.salesTaxes.service.serviceImp;

import com.ahmed.itsf.salesTaxes.model.Item;
import com.ahmed.itsf.salesTaxes.service.ITaxCalculator;
import com.ahmed.itsf.salesTaxes.util.Constants;
import com.ahmed.itsf.salesTaxes.util.TaxeUtils;
import org.springframework.stereotype.Service;

@Service
public class DefaultTaxCalculator implements ITaxCalculator {


    @Override
    public double calculateTax(Item item) {
        double tax = 0.0;
        int itemQuantity = Integer.parseInt(item.getName().split(" ")[0]);
        if (!item.isExempt()) {
            tax += item.getPrice() * itemQuantity * Constants.BASIC_TAX_RATE;
        }
        if (item.isImported()) {
            tax += item.getPrice() * itemQuantity * Constants.IMPORT_DUTY;
        }
        return TaxeUtils.roundAmount(tax);
    }


}
