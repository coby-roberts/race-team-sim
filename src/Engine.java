public class Engine {
    public String type;
    public double fuelConsumptionKmpl; // km/l
    public int power; // horsepower

    public Engine(String type, double fuelConsumptionKmpl, int power) {
        this.type = type;
        this.fuelConsumptionKmpl = fuelConsumptionKmpl;
        this.power = power;
    }

    public double getAccelerationBoost() {
        return power / 100.0;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%s Engine: Power=%d HP, Fuel Efficiency=%.1f km/l", type, power, fuelConsumptionKmpl);
    }
}