package cash.vo;

import java.util.List;

public class Cashbook {
	private int cashbookNo;
	private String category;
	private String cashbookDate;
	private String memberId;
	private int price;
	private String memo;
	private String updatedate;
	private String createdate;
	private List<String> hashtags;
    
    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }
    
    public List<String> getHashtags() {
        return hashtags;
    }
	public Cashbook() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cashbook(int cashbookNo, String category, String cashbookDate, String memberId, int price, String memo,
			String updatedate, String createdate) {
		super();
		this.cashbookNo = cashbookNo;
		this.category = category;
		this.cashbookDate = cashbookDate;
		this.memberId = memberId;
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
	public String getCashbookDate() {
		return cashbookDate;
	}
	public void setCashbookDate(String cashbookDate) {
		this.cashbookDate = cashbookDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	@Override
	public String toString() {
		return "Cashbook [cashbookNo=" + cashbookNo + ", category=" + category + ", cashbookDate=" + cashbookDate
				+ ", memberId=" + memberId + ", price=" + price + ", memo=" + memo + ", updatedate=" + updatedate
				+ ", createdate=" + createdate + "]";
	}
	
	
	
}
