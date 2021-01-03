package Car_Rental_Tests;

public class PerDayRent implements Comparable<PerDayRent> {
	public String vin;
	public float  price;
	public float  priceAfterDiscount;
	
	public PerDayRent(String vin, float price) {
		this.vin=vin;
		this.price=price;
	}

	public int compareTo(PerDayRent obj) {
		return (int)((float)this.price-obj.price);
	}
	

}
