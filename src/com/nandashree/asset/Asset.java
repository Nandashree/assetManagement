package com.nandashree.asset;

/**
 * @author nandashree.r
 *InventoryData.xlsx
 */
public class Asset {

	private int id;
	private String item;
	private String model;
	private String version;
	private int noOfItems;
	private int available;
	private String manufacturer;
	private int status;

	public Asset(int id, String item, String model, String version, int noOfItems, int available, String manufacturer,
			int status) {
		super();
		this.id = id;
		this.item = item;
		this.model = model;
		this.version = version;
		this.noOfItems = noOfItems;
		this.available = available;
		this.manufacturer = manufacturer;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Asset [id=" + id + ", item=" + item + ", model=" + model + ", version=" + version + ", noOfItems="
				+ noOfItems + ", available=" + available + ", manufacturer=" + manufacturer + ", status=" + status
				+ "]";
	}
}
