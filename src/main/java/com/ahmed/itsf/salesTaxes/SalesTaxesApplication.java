package com.ahmed.itsf.salesTaxes;

import com.ahmed.itsf.salesTaxes.model.dto.ItemRequest;
import com.ahmed.itsf.salesTaxes.model.dto.ReceiptResponse;
import com.ahmed.itsf.salesTaxes.service.IReciept;
import com.ahmed.itsf.salesTaxes.service.serviceImp.DefaultReceiptGenerator;
import com.ahmed.itsf.salesTaxes.service.serviceImp.DefaultTaxCalculator;
import com.ahmed.itsf.salesTaxes.service.serviceImp.Parser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SalesTaxesApplication {

	public static void main(String[] args) {

		SpringApplication.run(SalesTaxesApplication.class, args);

		String filePath;

		// Vérifier si un argument est passé
		if (args.length < 1) {
			// Si aucun argument, demander à l'utilisateur d'entrer un chemin de fichier
			Scanner scanner = new Scanner(System.in);
			System.out.print("Please provide the input file path: ");
			filePath = scanner.nextLine();
		} else {

			filePath = args[0];
		}




			Parser lineParser = new Parser();

			List<String> lines = lineParser.parseInputFile(filePath);
			List<ItemRequest> items = lineParser.parseInput(lines);
			IReciept receiptService = new DefaultReceiptGenerator( new DefaultTaxCalculator());
			ReceiptResponse receipt = receiptService.generateReceipt(items);

			receipt.getItems().forEach(item ->
					System.out.printf("%s: %.2f%n", item.getName(), item.getPrice())
			);
			System.out.printf("Sales Taxes: %.2f  ", receipt.getSalesTaxes());
			System.out.printf("Total: %.2f%n", receipt.getTotal());

	}
}



