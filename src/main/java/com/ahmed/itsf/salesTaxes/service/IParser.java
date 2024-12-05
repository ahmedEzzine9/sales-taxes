package com.ahmed.itsf.salesTaxes.service;

import com.ahmed.itsf.salesTaxes.model.dto.ItemRequest;

import java.util.List;

public interface IParser {
    /**
     * takes list of  strings representing lines
     *
     * @param inputLines
     * @return the parsed list of items
     */
    List<ItemRequest> parseInput(List<String> inputLines);

    /**
     * takes file path and return lines
     *
     * @param filePath
     * @return
     */
    List<String> parseInputFile(String filePath);
}
