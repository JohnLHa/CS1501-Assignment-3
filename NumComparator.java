/*John Ha
Assignment 3
CS1501 Tuesday/Thursday
*/

import java.util.Comparator;

//Class to compare the prices of the cars
public class NumComparator implements Comparator<Car> {
    public int compare(Car x, Car y){
        if(x.getPrice()<y.getPrice()) return -1;
        if(x.getPrice()>y.getPrice()) return 1;
        return 0;
    }
}
