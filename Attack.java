public class Attack extends Usable {
    private int accuracy; // As a percent.

    // Constructor
    public Attack(Usable usable) {
        this(90, usable);
    }
    public Attack(int accuracy, Usable usable) {
        super(usable.getName(), usable.isTargetUser(), usable.isSwitchTarget(), usable.getEffect());
        this.accuracy = accuracy;
    }
   
    // Getters/Setters
    public int getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }
}
