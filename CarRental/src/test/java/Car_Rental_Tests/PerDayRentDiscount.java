package Car_Rental_Tests;

public class PerDayRentDiscount implements Comparable<PerDayRentDiscount>{
	public String vin2;
	public float  price2;
	public float  perDayRentADiscount2;
	
	public PerDayRentDiscount(String vin2, float  perDayRentADiscount2) {
		this.vin2=vin2;
		this.perDayRentADiscount2=perDayRentADiscount2;
	}

	public int compareTo(PerDayRentDiscount obj) {
		return (int)((float)this.perDayRentADiscount2-obj.perDayRentADiscount2);
	}
	

}



