public class Item {
    private String name;
    private int power; // -1 if NA
    private String type; // Options: "heal", "shield", "power_boost"

    // Constructor
    public Item(String name, int power, String type) {
        this.name = name;
        this.power = power;
        this.type = type;
    }

    // Getters/Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
