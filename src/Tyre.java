public class Tyre {

    public enum TyreType {
        SOFT,
        MEDIUM,
        HARD
    }
    public TyreType type;
    public int durability; // km
    public double grip;    // 0.0 to 1.0

    public Tyre(TyreType type, int durability, double grip) {
        this.type = type;
        this.durability = durability;
        this.grip = grip;
    }

    public String getType() {
        String name = type.name(); // e.g. "SOFT"
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); // → "Soft"
    }

    @Override
    public String toString() {
        return String.format("%s Tyre: Durability=%d km, Grip=%.2f", type, durability, grip);
    }
}
