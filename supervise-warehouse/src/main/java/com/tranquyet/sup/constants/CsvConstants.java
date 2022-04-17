package com.tranquyet.sup.constants;

import java.util.Date;

public class CsvConstants {
	public static final String[] HEADERS_CSV = { "Mã ĐH", "Ngày chứng từ", "Nguồn bán hàng", "Nhân viên tạo đơn",
			"Trạng thái đơn hàng", "Tên khách hàng", "Điện thoại KH", "Địa chỉ KH", "Mã sản phẩm", "Tên sản phẩm",
			"Ghi chú sản phẩm", "Ghi chú đơn", "Trạng thái lịch" };
	public static String DIR_ROOT = System.getProperty("user.dir") + "_";
	public static String DIR_CSV = DIR_ROOT + createCsvName();

	public static String createCsvName() {
		StringBuilder name = new StringBuilder();
		name.append("schedule_").append(new Date().toInstant().toEpochMilli()).append(".csv");
		return name.toString();
	}
}
