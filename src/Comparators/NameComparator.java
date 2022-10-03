package Comparators;
import Data.Beer;

import java.util.Comparator;

public class NameComparator implements Comparator<Beer> {
    @Override
    public int compare(Beer o1, Beer o2) {
        return (o1.getName().compareTo(o2.getName()));
    }
}
