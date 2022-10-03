package Data;

import java.io.Serializable;
public class Beer implements Serializable {
    private final String name;
    private final String style;
    private final double strength;

    @Override
    public String toString() {
        return "Name=" + name +
                ", style=" + style +
                ", strength=" + strength;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public double getStrength() {
        return strength;
    }

    public Beer(String name, String style, double strength) {
        this.name = name;
        this.style = style;
        this.strength = strength;
    }
}
