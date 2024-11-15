public class Attack {
    private String name;
    private int power;
    private String type;
    private String element;

    // Constructor
    public Attack(String element, String name, int power, String type) {
        this.element = element;
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
    public String getElement() {
        return element;
    }
    public void setElement(String element) {
        this.element = element;
    } 
}
