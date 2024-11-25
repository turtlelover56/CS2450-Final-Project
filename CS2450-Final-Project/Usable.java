public class Usable {
   private String name;
   private boolean targetPlayer;
   private boolean switchTarget;
   private Effect effect;

   // Constructor
   public Usable(String name, boolean targetPlayer, boolean switchTarget, Effect effect) {
        this.name = name;
        this.switchTarget = switchTarget;
        this.targetPlayer = targetPlayer;
   }

   // Getters/Setters
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public boolean isTargetPlayer() {
      return targetPlayer;
   }
   public void setTargetPlayer(boolean targetPlayer) {
      this.targetPlayer = targetPlayer;
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
}
