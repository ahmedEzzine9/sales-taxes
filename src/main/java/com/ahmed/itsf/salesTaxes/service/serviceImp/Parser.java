package com.ahmed.itsf.salesTaxes.service.serviceImp;

import com.ahmed.itsf.salesTaxes.model.Item;
import com.ahmed.itsf.salesTaxes.model.dto.ItemRequest;
import com.ahmed.itsf.salesTaxes.service.IParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class Parser implements IParser {
    @Override
    public List<ItemRequest> parseInput(List<String> inputLines) {
        List<ItemRequest> items = new ArrayList<>();
        for (String line : inputLines) {
            String[] parts = line.split(" at ");
            String description = parts[0].trim();
            double price = Double.parseDouble(parts[1].trim());

            boolean isImported = description.contains("imported");
            boolean isExempt = description.contains("book") || description.contains("chocolate") || description.contains("pill");

            items.add(new ItemRequest(description, price, isImported, isExempt));
        }
        return items;
    }

    @Override
    public List<String> parseInputFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {

                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
