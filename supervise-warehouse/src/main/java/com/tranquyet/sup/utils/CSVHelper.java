package com.tranquyet.sup.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import com.tranquyet.sup.constants.CsvConstants;
import com.tranquyet.sup.dtos.OrderScheduleDTO;

public class CSVHelper {
	public static ByteArrayInputStream transferDbsToCsv(List<OrderScheduleDTO> orders) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			// TODO: print headers
			csvPrinter.printRecord(Arrays.asList(CsvConstants.HEADERS_CSV));
			orders.forEach(p -> {
				List<?> data = Arrays.asList(p.getOrderCode(), p.getTimeRelease(), p.getSource(), p.getStaff(),
						p.getStatusOrder(), p.getCustomerName(), p.getPhone(), p.getAddress(), p.getProductCode(),
						p.getProductName(), p.getProductNote(), p.getOrderNote(), p.getStatusOrderSchedule());
				try {
					csvPrinter.printRecord(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

	public static String writeCsvFile(List<OrderScheduleDTO> orders) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(CsvConstants.DIR_CSV), format);) {
			csvPrinter.printRecord(Arrays.asList(CsvConstants.HEADERS_CSV));
			orders.forEach(p -> {
				List<?> data = Arrays.asList(p.getOrderCode(), p.getTimeRelease(), p.getSource(), p.getStaff(),
						p.getStatusOrder(), p.getCustomerName(), p.getPhone(), p.getAddress(), p.getProductCode(),
						p.getProductName(), p.getProductNote(), p.getOrderNote(), p.getStatusOrderSchedule());
				try {
					csvPrinter.printRecord(data);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			csvPrinter.flush();
//			File file = new ClassPathResource("messages.properties").getFile();
			return CsvConstants.DIR_CSV;
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}
}