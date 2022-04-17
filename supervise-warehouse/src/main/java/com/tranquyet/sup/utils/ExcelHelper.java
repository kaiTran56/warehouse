package com.tranquyet.sup.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.tranquyet.sup.dtos.OrderScheduleDTO;
import com.tranquyet.sup.enums.StatusOrderEnum;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static String SHEET = "Sheet0";

	public static final Integer START_ROW = 4;

	public static boolean hasExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<OrderScheduleDTO> saveExcelToDatabase(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();
			List<OrderScheduleDTO> orders = new ArrayList<OrderScheduleDTO>();
			Integer maxRows = sheet.getPhysicalNumberOfRows();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				// skip header
				rowNumber++;
				if (rowNumber < START_ROW) {
					continue;
				}
				if (rowNumber == maxRows - 1) {
					break;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();
				OrderScheduleDTO order = new OrderScheduleDTO();
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellIdx) {
					case 0 -> order.setOrderCode(currentCell.getStringCellValue());
					case 1 -> order.setTimeRelease(CustomFormatDate.convertStringToDate(
							currentCell.getStringCellValue(), CustomFormatDate.FORMAT_DD_MM_YYYY_HHmmss));
					case 2 -> order.setSource(currentCell.getStringCellValue());
					case 3 -> order.setStaff(currentCell.getStringCellValue());
					case 4 -> order.setStatusOrder(currentCell.getStringCellValue());
					case 5 -> order.setCustomerName(currentCell.getStringCellValue());
					case 6 -> order.setPhone(currentCell.getStringCellValue());
					case 7 -> order.setAddress(currentCell.getStringCellValue());
					case 8 -> order.setProductCode(currentCell.getStringCellValue());
					case 9 -> order.setProductName(currentCell.getStringCellValue());
					case 10 -> order.setProductNote(currentCell.getStringCellValue());
					case 11 -> order.setOrderNote(currentCell.getStringCellValue());
					}
					cellIdx++;
				}
				order.setDeleteStatus(1);
				order.setStatusOrderSchedule(StatusOrderEnum.WAITING.getValue());
				orders.add(order);
			}
			System.out.println(orders.size());
			workbook.close();
			return orders.stream().filter(p -> p.getStatusOrder().equals(StatusOrderEnum.COMPLETE.getValue()))
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}