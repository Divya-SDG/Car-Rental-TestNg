package Car_Rental_Tests;

public class CarRevenue implements Comparable<CarRevenue> {

	public String vin;
	public float car_revenue;
	public CarRevenue(String vin, float car_revenue) {
		this.vin=vin;
		this.car_revenue=car_revenue;
	}

	public int compareTo(CarRevenue obj) {
		return(int) ((float)this.car_revenue-obj.car_revenue);
	}

}
