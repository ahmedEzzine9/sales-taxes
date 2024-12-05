package com.ahmed.itsf.salesTaxes.service;

import com.ahmed.itsf.salesTaxes.model.dto.ItemRequest;
import com.ahmed.itsf.salesTaxes.model.dto.ReceiptResponse;

import java.util.List;

public interface IReciept {
    /**
     * generates Receipt from items List
     *
     * @param items
     * @return
     */
    ReceiptResponse generateReceipt(List<ItemRequest> items);

}
