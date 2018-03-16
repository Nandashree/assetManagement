package com.nandashree.poi;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nandashree.asset.Asset;
import com.nandashree.asset.Order;
import com.nandashree.emp.Employee;

/**
 * @author nandashree.r
 *
 */
public class ApachePOIExcelRead {

	public static Map<Integer, Asset> loadAssetDb(File file) {

		Map<Integer, Asset> db = new HashMap<>();
		try {
			FileInputStream excelFile = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int i = 0;
			while (iterator.hasNext()) {

				Row row = iterator.next();
				if (i > 0) {
					row.getCell(4);
					int id = (int) row.getCell(0).getNumericCellValue();
					String item = row.getCell(1).getStringCellValue();
					String model = row.getCell(2).getStringCellValue();
					String version = row.getCell(3).getStringCellValue();
					int noOfItems = (int) row.getCell(4).getNumericCellValue();
					int available = (int) row.getCell(5).getNumericCellValue();
					String manufacturer = row.getCell(6).getStringCellValue();
					int status = 0;
					Asset asset = new Asset(id, item, model, version, noOfItems, available, manufacturer, status);
					db.put(id, asset);
				}
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return db;
	}

	public static Map<Integer, Employee> loadEmployeeDb(File file) {

		Map<Integer, Employee> db = new HashMap<>();
		try {
			FileInputStream excelFile = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int i = 0;
			while (iterator.hasNext()) {

				Row row = iterator.next();
				if (i > 0) {
					row.getCell(4);
					int id = (int) row.getCell(0).getNumericCellValue();
					Employee emp = new Employee(id, row.getCell(1).getStringCellValue(),
							row.getCell(2).getStringCellValue());
					db.put(id, emp);
				}
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;
	}

	public static Map<Long, Order> loadOrderDb(File assetDataFile) {

		Map<Long, Order> db = new HashMap<>();
		try {
			FileInputStream excelFile = new FileInputStream(assetDataFile);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int i = 0;
			while (iterator.hasNext()) {

				Row row = iterator.next();
				if (i > 0) {
					
					long orderId = (long) row.getCell(0).getNumericCellValue();
					int id = (int) row.getCell(1).getNumericCellValue();
					int empId = (int) row.getCell(2).getNumericCellValue();
					int quantity = (int) row.getCell(3).getNumericCellValue();
					long orderDate = (long) row.getCell(4).getNumericCellValue();
					long deliverDate = (long) row.getCell(5).getNumericCellValue();
					int status = (int) row.getCell(6).getNumericCellValue();
					
					Order order = new Order(orderId, id, empId, quantity, orderDate, deliverDate, status);
					db.put(orderId, order);
				}
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return db;
	}
}
