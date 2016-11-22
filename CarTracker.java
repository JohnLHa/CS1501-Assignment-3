/*John Ha
Assignment 3
CS1501 Tuesday/Thursday
*/

import java.util.*;

public class CarTracker{
	public static void main(String[] args){

		String VIN, make, model, color;
		double price, mileage;
		boolean test=true;//Makes sure VIN is entered properly
		int i;
		int input;
		
		Scanner kb = new Scanner(System.in);
		System.out.println("Hello. You have arrived at your list of cars you are interested in.");

		//Creates a priority queue based on comparing prices
		Comparator<Car> priceComparator = new NumComparator();
		PriorityQueue<Car> priceQueue = new PriorityQueue<>(100, priceComparator);

		//Creates a priority queue based on comparing mileage
		Comparator<Car> mileComparator = new MileComparator();
		PriorityQueue<Car> mileQueue = new PriorityQueue<>(100, mileComparator);
		
		do{
			menu();//Just calls the basic interface full of print statements
			input = kb.nextInt();
		
			switch(input){//Chooses from one of the interface options
				case 1:	//Adds a car
					System.out.println("\nPlease enter the following car information: ");
					System.out.print("Please enter the 17 character VIN(no I(i), O(o), or Q(q): ");
					VIN = kb.next();
					VIN = VIN.toUpperCase();
				
					while(test){//Entering the VIN info with required restrictions
						if(VIN.length()!=17){
							System.out.println("Sorry, a VIN needs 17 characters/numbers.");
						}
					
						else{
							if(VIN.contains("I")||VIN.contains("Q")||VIN.contains("O")){
								System.out.println("Sorry, you have an invalid character in your VIN");
							}
							else{
								break;
							}
						}
						System.out.print("Please enter the 17 character VIN(no I(i), O(o), or Q(q): ");
						VIN = kb.next();
						VIN = VIN.toUpperCase();
					}

					//Has the user enter the rest of the car information
					System.out.print("Please enter the car's make(e.g., Ford, Toyota, Honda): ");
					make = kb.next();
					System.out.print("Please enter the car's model(e.g., Fiesta, Camry, Civic): ");
					model = kb.next();
					System.out.print("Please enter the price to purchase(in dollars): ");
					price = kb.nextDouble();
					System.out.print("Please enter the mileage: ");
					mileage = kb.nextDouble();
					System.out.print("Please enter the color: ");
					color = kb.next();
				
					//Adds the car to the price and mile priority queue
					Car addCar = new Car(VIN, make, model, price, mileage, color);
					boolean success = priceQueue.add(addCar);
					boolean second = mileQueue.add(addCar);

					if(success&&second)
						System.out.println("Car successfully added to list.\n");
					else
						System.out.println("Car was unable to be added.\n");
				
					break;//ends the add car subsection
					
				case 2: // Updates a car
					System.out.println("\nYou have chosen to update a car.");

					//A quick check to make sure the priority queue is not empty.
					if(priceQueue.size()==0){
						System.out.println("Database is empty. Please add a car first.");
						break;
					}
					System.out.print("Please enter the VIN number of the car you would like to update: ");
					String findVIN = kb.next();
					findVIN = findVIN.toUpperCase();

					//Creates two indexable arrays to search through
					Car[] arrayP = priceQueue.toArray(new Car[priceQueue.size()]);
					Car[] arrayM = mileQueue.toArray(new Car[mileQueue.size()]);

					//Iterates through both arrays to find the index of the car that is being updated
					String find;
					int place = -1;
					for(i=0; i<arrayP.length; i++){
						find = arrayP[i].getVIN();
						if(findVIN.equals(find)){
							place = i;
							break;
						}
					}
					int placeM = -1;
					for(i=0; i<arrayM.length; i++){
						find = arrayM[i].getVIN();
						if(findVIN.equals(find)){
							placeM = i;
							break;
						}
					}
					if(place==-1){
						System.out.println("Sorry, the VIN was not in the database. Exiting.");
						break;
					}
					if(placeM==-1){
						System.out.println("Sorry, the VIN was not in the database. Exiting.");
						break;
					}

					//Prompts the user to update a section
					System.out.println("What would you like to update?");
					System.out.println("1. Price");
					System.out.println("2. Mileage");
					System.out.println("3. Color");
					int choice = kb.nextInt();

					//Updates the car in both arrays
					if(choice==1){
						System.out.print("Change the price to: ");
						double newVal = kb.nextDouble();
						arrayP[place].setPrice(newVal);
						arrayM[placeM].setPrice(newVal);
					}
					if(choice==2){
						System.out.print("Change the mileage to: ");
						double newVal = kb.nextDouble();
						arrayP[place].setMileage(newVal);
						arrayM[placeM].setMileage(newVal);
					}
					if(choice==3){
						System.out.print("Change the color to: ");
						String newVal = kb.next();
						arrayP[place].setColor(newVal);
						arrayM[place].setColor(newVal);
					}
					break;

				case 3: //Removes a specific car from consideration
					System.out.println("\nYou have chosen to remove a car.");

					//A quick check to make sure the priority queue is not empty.
					if(priceQueue.size()==0){
						System.out.println("Database is empty. Please add a car first.");
						break;
					}

					System.out.print("Please enter the VIN number of the car you would like to remove: ");
					findVIN = kb.next();
					findVIN = findVIN.toUpperCase();

					//Following finds the car object first, then removes from the queue using the built in method
					arrayP = priceQueue.toArray(new Car[priceQueue.size()]);

					place = -1;
					for(i=0; i<arrayP.length; i++){
						find = arrayP[i].getVIN();
						if(findVIN.equals(find)){
							place = i;
							break;
						}
					}
					if(place==-1){
						System.out.println("Sorry, the VIN was not in the database. Exiting.");
						break;
					}

					Car removing = arrayP[place];
					priceQueue.remove(removing);
					mileQueue.remove(removing);

					break;
				case 4: //Retrieve lowest price car
					System.out.println("\nYou have chosen to retrieve the lowest priced car");

					//A quick check to make sure the priority queue is not empty.
					if(priceQueue.size()==0){
						System.out.println("Database is empty. Please add a car first.");
						break;
					}
					//Uses built in peek method to find min of the minHeap
					Car lowest = priceQueue.peek();
					lowest.getEverything();
					break;
				case 5: //Retrieve lowest mileage car
					System.out.println("\nYou have chosen to retrieve the lowest mileage car.");

					//A quick check to make sure the priority queue is not empty.
					if(priceQueue.size()==0){
						System.out.println("Database is empty. Please add a car first.");
						break;
					}
					//Uses built in peek method to find min of the minHeap
					lowest = mileQueue.peek();
					lowest.getEverything();
					break;
				case 6: // Retrieve lowest price car by make and model
					System.out.println("\nYou have chosen to retrieve the lowest priced car by make and model.");

					//A quick check to make sure the priority queue is not empty.
					if(priceQueue.size()==0){
						System.out.println("Database is empty. Please add a car first.");
						break;
					}

					System.out.print("Please enter in the make you are looking for: ");
					String findMake = kb.next();
					System.out.print("Please enter in the model you are looking for: ");
					String findModel = kb.next();

					//Makes another heap copy that can continuously remove the min when the restrictions are not met
					PriorityQueue<Car> priceCopy = priceQueue;
					Car x = priceCopy.peek();
					while(x!=null){
						x = priceCopy.peek();
						if(x.getMake().equals(findMake)){
							if(x.getModel().equals(findModel)){
								x.getEverything();
								break;
							}
							else{
								priceCopy.poll();
							}
						}
						else{
							priceCopy.poll();
						}
					}
					if(x==null){
						System.out.println("That car does not exist in the database.");
					}

					break;
				case 7: //Retrieve lowest mileage car by make and model
					System.out.println("\nYou have chosen to retrieve the lowest mileage car by make and model.");

					//A quick check to make sure the priority queue is not empty.
					if(priceQueue.size()==0){
						System.out.println("Database is empty. Please add a car first.");
						break;
					}

					System.out.print("Please enter in the make you are looking for: ");
					findMake = kb.next();
					System.out.print("Please enter in the model you are looking for: ");
					findModel = kb.next();

					//Makes another heap copy that can continuously remove the min when the restrictions are not met
					PriorityQueue<Car> mileCopy = mileQueue;
					Car y = mileCopy.peek();
					while(y!=null){
						x = mileCopy.peek();
						if(x.getMake().equals(findMake)){
							if(x.getModel().equals(findModel)){
								x.getEverything();
								break;
							}
							else{
								mileCopy.poll();
							}
						}
						else{
							mileCopy.poll();
						}
					}
					if(y==null){
						System.out.println("That car does not exist in the database.");
					}
					break;
			}
		}
		while(input!=8);//Number 8 closes the program.
	}
	
	private static void menu(){
		System.out.println("What would you like to do?");
		System.out.println("1. Add a car to your list.");
		System.out.println("2. Update a car in your list.");
		System.out.println("3. Remove a specific car from your list.");
		System.out.println("4. Retrieve the lowest priced car.");
		System.out.println("5. Retrieve the lowest mileage car.");
		System.out.println("6. Retrieve the lowest priced car by make and model.");
		System.out.println("7. Retrieve the lowest mileage car by make and model.");	
		System.out.println("8. Exit the program.");			
		System.out.print("Please enter your choice(1-8): ");
	}
	
}
