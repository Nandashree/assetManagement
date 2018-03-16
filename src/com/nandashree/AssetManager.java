package com.nandashree;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.nandashree.asset.Asset;
import com.nandashree.asset.Order;
import com.nandashree.asset.Order.OrderStatus;
import com.nandashree.emp.Employee;
import com.nandashree.poi.ApachePOIExcelRead;
import com.nandashree.poi.ApachePOIExcelWrite;

/**
 * @author nandashree.r
 *
 */
public class AssetManager {

	private static Map<Integer, Employee> empDb = new HashMap<>();

	private static Map<Integer, Asset> assetDb = new HashMap<>();

	private static Map<Long, Order> orderDb = new HashMap<>();

	public AssetManager() {
	}

	public static int menu() {

		int selection;
		Scanner input = new Scanner(System.in);

		/***************************************************/

		System.out.println("Choose from these choices");
		System.out.println("-------------------------\n");
		System.out.println("1 - List Assets");
		System.out.println("2 - Request Asset");
		System.out.println("3 - Show order list");
		System.out.println("4 - Track employee Order");
		System.out.println("5 - Save and Quit");

		selection = input.nextInt();
		return selection;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Welcome to IT asset management");
		System.out.println("-------------------------\n");

		File employeeDataFile = new File("D:\\tutorial\\InfraAssetManagement\\resources\\EmployeeData.xlsx");
		File inventoryDataFile = new File("D:\\tutorial\\InfraAssetManagement\\resources\\InventoryData.xlsx");
		File assetDataFile = new File("D:\\tutorial\\InfraAssetManagement\\resources\\AssetRequestData.xlsx");

		empDb = ApachePOIExcelRead.loadEmployeeDb(employeeDataFile);

		assetDb = ApachePOIExcelRead.loadAssetDb(inventoryDataFile);

		orderDb = ApachePOIExcelRead.loadOrderDb(assetDataFile);

		while (true) {
			int choice = menu();

			switch (choice) {
			case 1:
				listAssets();
				break;
			case 2:
				requestAsset();
				break;
			case 3:
				showOrders();
				break;

			case 4:
				trackOrder();
				break;
			case 5:

				ApachePOIExcelWrite.writeEmployeeDb(employeeDataFile, empDb);

				ApachePOIExcelWrite.writeAssetDb(inventoryDataFile, assetDb);

				ApachePOIExcelWrite.writeOrderDb(assetDataFile, orderDb);

				System.exit(0);
				break;
			default:
				// The user input an unexpected choice.
			}
		}
	}

	private static void showOrders() {
		if (orderDb.size() > 0) {
			System.out.println("\nIT - Order List");
			System.out.println("-------------------------");
			for (Entry<Long, Order> entry : orderDb.entrySet()) {
				System.out.println(entry.getValue());
			}
			System.out.println();
		} else {
			System.out.println("No orders placed");
		}

	}

	private static void trackOrder() {
		System.out.println("Please enter Order number");
		Scanner input = new Scanner(System.in);// TODO Auto-generated method stub
		long orderId = input.nextLong();
		if (orderDb.containsKey(orderId)) {

			Order order = orderDb.get(orderId);
			System.out.println(orderDb.get(orderId));

			OrderStatus arr[] = OrderStatus.values();
			int i = 0;
			System.out.println("StatusCode - Status");
			for (i = 0; i < arr.length; i++) {
				System.out.println(i + " - " + arr[i]);
			}
			System.out.println(i + " - Goto main menu");

			int status = input.nextInt();

			if (status != i) {
				order.setStatus(status);
				orderDb.put(orderId, order);
			}
		} else {
			System.err.println("You have entered wrong order number");
		}

	}

	private static void requestAsset() {
		long orderId = System.currentTimeMillis();
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter employee ID");
		int empId = input.nextInt();

		if (empDb.containsKey(empId)) {

			System.out.println("Please select below assetId");
			listAssets();

			int assetId = input.nextInt();
			if (assetDb.containsKey(assetId)) {
				Asset item = assetDb.get(assetId);
				if (item.getAvailable() > 0) {
					System.out.println("Item available quantity: " + item.getAvailable());
					getOrderDetails(orderId, empId, item);
				} else {
					System.out.println(
							"Request asset Stock is not there Need to procure\n Do you want to proceed (yes/no)?");
					String confirmation = input.next();
					if (confirmation.equalsIgnoreCase("yes")) {
						getOrderDetails(orderId, empId, item);
					}
				}

			} else {
				System.out.println("Sorry!! Requested asset is not available in IT department");
			}
		} else {
			System.err.println("Not a valid EmpID");
		}
	}

	private static void getOrderDetails(long orderId, int empId, Asset item) {
		Scanner input = new Scanner(System.in);

		System.out.println("\nPlease enter the quantity in numbers");
		int assetQty = input.nextInt();
		System.out.println("OrderID:" + orderId + ", Order placed successfully");
		if (assetQty > item.getAvailable()) {
			placeOrderItem(orderId, item.getId(), empId, assetQty, Order.OrderStatus.INPROGRESS.ordinal());
			System.out.println("Requested Item will be delivered to you within 5 day");
		} else {
			item.setAvailable(item.getAvailable() - assetQty);
			assetDb.put(item.getId(), item);
			placeOrderItem(orderId, item.getId(), empId, assetQty, Order.OrderStatus.ORDERED.ordinal());
			System.out.println("Requested Item will be delivered to you within a day");
		}
	}

	private static void placeOrderItem(long orderId, int id, int empId, int quantity, int status) {

		Date orderDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(orderDate); // Now use today date.
		c.add(Calendar.DATE, 5); // Adding 5 days

		Order order = new Order(orderId, id, empId, quantity, orderDate.getTime(), c.getTimeInMillis(), status);
		orderDb.put(orderId, order);
	}

	private static void listAssets() {
		System.out.println("AssetID - Asset");
		for (Entry<Integer, Asset> entry : assetDb.entrySet()) {
			System.out.println(entry.getValue().getId() + " - " + entry.getValue().getItem() + ", available QTY:"
					+ entry.getValue().getAvailable());
		}

		System.out.println();
	}

}
