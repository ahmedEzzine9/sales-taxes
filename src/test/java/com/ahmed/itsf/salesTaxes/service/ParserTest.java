package com.ahmed.itsf.salesTaxes.service;

import com.ahmed.itsf.salesTaxes.model.dto.ItemRequest;
import com.ahmed.itsf.salesTaxes.service.serviceImp.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseInputFile() throws IOException {

        Parser parser = new Parser();

        List<String> lines = parser.parseInputFile("src/test/java/resources/input1.txt");
        List<ItemRequest> items = parser.parseInput(lines);
        assertEquals(3, items.size());
        assertEquals("1 book", items.get(0).getName());
        assertEquals(12.49, items.get(0).getPrice());
        assertFalse(items.get(0).isImported());
        assertTrue(items.get(0).isExempt());

        assertEquals("1 music CD", items.get(1).getName());
        assertEquals(14.99, items.get(1).getPrice());
        assertFalse(items.get(1).isImported());
        assertFalse(items.get(1).isExempt());

        assertEquals("1 chocolate bar", items.get(2).getName());
        assertEquals(0.85, items.get(2).getPrice());
        assertFalse(items.get(2).isImported());
        assertTrue(items.get(2).isExempt());
    }
}

