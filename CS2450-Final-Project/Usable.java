public class Usable {
   private String name;
   private boolean targetUser;
   private boolean switchTarget;
   private Effect effect;

   // Constructor
   public Usable(String name, boolean targetUser, boolean switchTarget, Effect effect) {
        this.name = name;
        this.switchTarget = switchTarget;
        this.targetUser = targetUser;
        this.effect = effect;
   }

   // Getters/Setters
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public boolean isTargetUser() {
      return targetUser;
   }
   public void setTargetUser(boolean targetUser) {
      this.targetUser = targetUser;
   }
   public boolean isSwitchTarget() {
      return switchTarget;
   }
   public void setSwitchTarget(boolean switchTarget) {
      this.switchTarget = switchTarget;
   }
   public Effect getEffect() {
      return effect;
   }
   public void setEffect(Effect effect) {
      this.effect = effect;
   }

   // toString
   @Override
   public String toString() {
      return "Usable [name=" + name + ", targetUser=" + targetUser + ", switchTarget=" + switchTarget + ", effect="
            + effect + "]";
   }
}
