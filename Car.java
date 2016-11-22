/*John Ha
Assignment 3
CS1501 Tuesday/Thursday
*/

public class Car{
	private String VIN, make, model, color;
	private double price, mileage;
	
	public Car(){
		
	}
	public Car(String vinCar, String makeCar, String modelCar, double value, double miles, String colorCar){
		VIN = vinCar;
		make = makeCar;
		model = modelCar;
		price = value;
		mileage = miles;
		color = colorCar;
	}
	
	//setters
	public void setColor(String colorName){
		color = colorName;
	}
	public void setPrice(double priceNum){
		price = priceNum;
	}
	public void setMileage(double mileageNum){
		mileage = mileageNum;
	}

	//getters
	public String getVIN(){
		return VIN;
	}
	public String getMake(){
		return make;
	}
	public String getModel(){
		return model;
	}
	public double getPrice(){
		return price;
	}
	public double getMileage(){
		return mileage;
	}
	public void getEverything(){
		System.out.println("-------------------------");
		System.out.println("VIN Number is " + VIN);
		System.out.println("Car's make is " + make);
		System.out.println("Car's model is " + model);
		System.out.println("Car's price is " + price);
		System.out.println("Car's mileage is " + mileage);
		System.out.println("Car's color is " + color);
		System.out.println("-------------------------");
	}

}