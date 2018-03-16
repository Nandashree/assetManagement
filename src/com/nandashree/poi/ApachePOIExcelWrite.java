package com.nandashree.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nandashree.asset.Asset;
import com.nandashree.asset.Order;
import com.nandashree.emp.Employee;

/**
 * @author nandashree.r
 *
 */
public class ApachePOIExcelWrite {

	public static boolean writeAssetDb(File file, Map<Integer, Asset> asset) {
		try {
			FileInputStream excel = new FileInputStream(file);

			XSSFWorkbook workbook = new XSSFWorkbook(excel);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int i = 1;
			Cell cell = null;
			for (Entry<Integer, Asset> entry : asset.entrySet()) {
				XSSFRow sheetrow = sheet.getRow(i);
				if (sheetrow == null) {
					sheetrow = sheet.createRow(i);
				}
				
				cell = sheetrow.getCell(0);
				cell.setCellValue(entry.getValue().getId());
				
				cell = sheetrow.getCell(1);
				cell.setCellValue(entry.getValue().getItem());
				
				cell = sheetrow.getCell(2);
				cell.setCellValue(entry.getValue().getModel());

				cell = sheetrow.getCell(3);
				cell.setCellValue(entry.getValue().getVersion());
			
				cell = sheetrow.getCell(4);
				cell.setCellValue(entry.getValue().getNoOfItems());

				cell = sheetrow.getCell(5);
				cell.setCellValue(entry.getValue().getAvailable());

				cell = sheetrow.getCell(6);
				cell.setCellValue(entry.getValue().getManufacturer());

				cell = sheetrow.getCell(7);
				cell.setCellValue(entry.getValue().getStatus());

				i++;
			}
			excel.close();
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean writeEmployeeDb(File file,Map<Integer, Employee> employees) {
		try {
			FileInputStream excel = new FileInputStream(file);

			XSSFWorkbook workbook = new XSSFWorkbook(excel);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int i = 1;
			Cell cell = null;
			for (Entry<Integer, Employee> entry : employees.entrySet()) {
				XSSFRow sheetrow = sheet.getRow(i);
				if (sheetrow == null) {
					sheetrow = sheet.createRow(i);
				}
				
				cell = sheetrow.createCell(0);
				cell = sheetrow.getCell(0);
				cell.setCellValue(entry.getValue().getEmpId());
				
				cell = sheetrow.createCell(1);
				cell = sheetrow.getCell(1);
				cell.setCellValue(entry.getValue().getEmpName());
				
				cell = sheetrow.createCell(2);
				cell = sheetrow.getCell(2);
				cell.setCellValue(entry.getValue().getDepartment());
				
				i++;
			}
			excel.close();
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeOrderDb(File file, Map<Long, Order> orders) {
		try {
			System.out.println("writeOrderDb");
			FileInputStream excel = new FileInputStream(file);

			XSSFWorkbook workbook = new XSSFWorkbook(excel);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int i = 1;
			Cell cell = null;
			for (Entry<Long, Order> entry : orders.entrySet()) {
				System.out.println(entry.getValue());
				XSSFRow sheetrow = sheet.getRow(i);
				if (sheetrow == null) {
					sheetrow = sheet.createRow(i);
				}
				
				cell = sheetrow.createCell(0);
				cell = sheetrow.getCell(0);
				cell.setCellValue(entry.getValue().getOrderId());

				cell = sheetrow.createCell(1);
				cell = sheetrow.getCell(1);
				cell.setCellValue(entry.getValue().getId());

				cell = sheetrow.createCell(2);
				cell = sheetrow.getCell(2);
				cell.setCellValue(entry.getValue().getEmpId());

				cell = sheetrow.createCell(3);
				cell = sheetrow.getCell(3);
				cell.setCellValue(entry.getValue().getQuantity());

				cell = sheetrow.createCell(4);
				cell = sheetrow.getCell(4);
				cell.setCellValue(entry.getValue().getOrderDate());

				cell = sheetrow.createCell(5);
				cell = sheetrow.getCell(5);
				cell.setCellValue(entry.getValue().getDeliverDate());

				cell = sheetrow.createCell(6);
				cell = sheetrow.getCell(6);
				cell.setCellValue(entry.getValue().getStatus());

				i++;
			}
			excel.close();
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			outFile.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
