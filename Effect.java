public class Effect {
    private int generalType; 
    /*  0 - Damage
        1 - Boost
        2 - Heal */
    private int specificType;
    /*  -2 - Shield
	    -1 - None
	     0 - Fire
	     1 - Water
	     2 - Electricity
	     3 - Wood */
    private int power;
    private int duration;
    private boolean percent;

    // Constants
    public static final int DAMAGE = 0;
    public static final int BOOST = 1;
    public static final int HEAL = 2;

    public static final int SHIELD = -2;
    public static final int NONE = -1;
    public static final int FIRE = 0;
    public static final int WATER = 1;
    public static final int ELECTRICITY = 2;
    public static final int WOOD = 3;
    
    // Constructors
    /* Constructor with duration set to 0 for an instantaneous effect. */
    public Effect(int generalType, int specificType, int power, boolean percent) {
        this(generalType, specificType, power, 0, percent);
    }
    public Effect(int generalType, int specificType, int power, int duration, boolean percent) {
        this.duration = duration;
        this.generalType = generalType;
        this.percent = percent;
        this.power = power;
        this.specificType = specificType;
    }

    // Getters and Setters
    public int getGeneralType() {
        return generalType;
    }
    public void setGeneralType(int generalType) {
        this.generalType = generalType;
    }
    public int getSpecificType() {
        return specificType;
    }
    public void setSpecificType(int specificType) {
        this.specificType = specificType;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public boolean isPercent() {
        return percent;
    }
    public void setPercent(boolean percent) {
        this.percent = percent;
    }
    
    // toString
    @Override
    public String toString() {
        return "Effect [generalType=" + generalType + ", specificType=" + specificType + ", power=" + power
                + ", duration=" + duration + ", percent=" + percent + "]";
    }
}
