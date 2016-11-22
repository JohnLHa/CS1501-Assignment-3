/*John Ha
Assignment 3
CS1501 Tuesday/Thursday
*/

import java.util.Comparator;

public class MileComparator implements Comparator<Car> {
    public int compare(Car x, Car y){
        if(x.getMileage()<y.getMileage()) return -1;
        if(x.getMileage()>y.getMileage()) return 1;
        return 0;
    }
}
