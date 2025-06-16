import java.lang.reflect.AnnotatedType;

public class Car {
    Engine engine;
    Tyre tyre;
    AeroKit aeroKit;

    public Car(Engine engine, Tyre tyre, AeroKit aeroKit) {
        if (engine == null) {
            throw new IllegalArgumentException("Engine cannot be null");
        }
        if (tyre == null) {
            throw new IllegalArgumentException("Tyre cannot be null");
        }
        if (aeroKit == null) {
            throw new IllegalArgumentException("AeroKit cannot be null");
        }

        this.engine = engine;
        this.tyre = tyre;
        this.aeroKit = aeroKit;
    }

    public double getTopSpeed() {
        // Use the aero kit's provided top speed adjusted by engine power ratio
        return aeroKit.topSpeedKmh * (engine.power / 350.0);
    }

    public double getFuelUsagePerKm() {
        // Baseline aero efficiency to normalize against
        final double baselineAeroEfficiency = 12.0;
        final double fuelConsumptionScale = 3.0;

        // Calculate aero factor: >1 means better efficiency, <1 worse efficiency
        double aeroFactor = baselineAeroEfficiency / aeroKit.fuelEfficiencyKmpl;

        // Engine fuel consumption in L/km
        double engineConsumption = 1.0 / engine.fuelConsumptionKmpl;

        // Total fuel consumption = engine consumption * aero factor
        return engineConsumption * aeroFactor * fuelConsumptionScale;
    }

    public double getHandlingScore() {
        // Combine tyre grip and aero cornering rating for handling
        return tyre.grip * 10 + aeroKit.corneringRating;
    }

    public double getAcceleration() {
        return engine.getAccelerationBoost() - aeroKit.dragCoefficient * 10;
    }

    public double getTyreWearRate() {
        return 1.0 / tyre.durability;
    }

    @Override
    public String toString() {
        return "Car Configuration:\n" +
                engine + "\n" +
                tyre + "\n" +
                aeroKit + "\n" +
                String.format("Top Speed: %.1f km/h%nFuel Usage per km: %.3f L%nHandling Score: %.2f%nAcceleration Score: %.2f%nTyre Wear Rate: %.4f per km%n",
                        getTopSpeed(), getFuelUsagePerKm(), getHandlingScore(), getAcceleration(), getTyreWearRate());
    }


    public Engine getEngine() {
        return engine;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public AeroKit getAeroKit() {
        return aeroKit;
    }
}
