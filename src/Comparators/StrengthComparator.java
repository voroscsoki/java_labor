package Comparators;
import Data.Beer;

import java.util.Comparator;

public class StrengthComparator implements Comparator<Beer> {
    @Override
    public int compare(Beer o1, Beer o2) {
        return Double.compare(o1.getStrength(), o2.getStrength());
    }
}
