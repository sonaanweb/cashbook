package cash.vo;

// private + (source) super + toString + get/set
public class Cashbook {
	private int cashbookNo;
	private String category;
	private String cashBookDate;
	private int price;
	private String memo;
	private String updatedate;
	private String createdate;
	
	public Cashbook() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cashbook [cashbookNo=" + cashbookNo + ", category=" + category + ", cashBookDate=" + cashBookDate
				+ ", price=" + price + ", memo=" + memo + ", updatedate=" + updatedate + ", createdate=" + createdate
				+ "]";
	}

	public Cashbook(int cashbookNo, String category, String cashBookDate, int price, String memo, String updatedate,
			String createdate) {
		super();
		this.cashbookNo = cashbookNo;
		this.category = category;
		this.cashBookDate = cashBookDate;
		this.price = price;
		this.memo = memo;
		this.updatedate = updatedate;
		this.createdate = createdate;
	}

	public int getCashbookNo() {
		return cashbookNo;
	}

	public void setCashbookNo(int cashbookNo) {
		this.cashbookNo = cashbookNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCashBookDate() {
		return cashBookDate;
	}

	public void setCashBookDate(String cashBookDate) {
		this.cashBookDate = cashBookDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
	
}
