package Comparators;
import Data.Beer;

import java.util.Comparator;

public class StyleComparator implements Comparator<Beer> {
    @Override
    public int compare(Beer o1, Beer o2) {
        return o1.getStyle().compareTo(o2.getStyle());
    }
}
