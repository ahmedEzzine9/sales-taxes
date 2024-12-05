package com.ahmed.itsf.salesTaxes.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxeUtils {
    /**
     * round the amount
     *
     * @param amount
     * @return
     */
    public static double roundAmount(double amount) {
        BigDecimal value = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
        return Math.ceil(value.doubleValue() * 20.0) / 20.0;
    }
}
