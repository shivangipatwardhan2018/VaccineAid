package main.java;

/**
 * Created by shivangipatwardhan on 11/26/16.
 */
public enum HealthTips {
    ONE("Remember to constantly wash your hands!"),
    TWO("Drinking 8 glasses for water is important for hydration"),
    THREE("Ensure that your source of drinking water is not stagnant, this causes bacteria to grow");

    private final String name;

    private HealthTips(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String getValue() {
        return this.name;
    }
}
