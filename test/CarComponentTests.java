import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarComponentTests {

    @Test
    public void testValidCarConfiguration() {
        Engine engine = EngineFactory.get("Turbocharged");
        Tyre tyre = TyreFactory.get("Soft");
        AeroKit aeroKit = AeroKitFactory.get("Ground Effect Kit");

        Car car = new Car(engine, tyre, aeroKit);

        assertEquals("Turbocharged", car.getEngine().getType());
        assertEquals("Soft", car.getTyre().getType());
        assertEquals("Ground Effect Kit", car.getAeroKit().getType());
    }

    @Test
    public void testCarPerformanceMetrics() {
        Engine engine = EngineFactory.get("Standard");
        Tyre tyre = TyreFactory.get("Medium");
        AeroKit aeroKit = AeroKitFactory.get("Standard Kit");

        Car car = new Car(engine, tyre, aeroKit);

        assertTrue(car.getTopSpeed() > 0);
        assertTrue(car.getFuelUsagePerKm() > 0);
    }

    @Test
    public void testNullEngineThrowsException() {
        Tyre tyre = TyreFactory.get("Hard");
        AeroKit aeroKit = AeroKitFactory.get("Standard Kit");

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(null, tyre, aeroKit);
        });
    }

    @Test
    public void testIncompatibleTyreThrowsException() {
        Engine engine = EngineFactory.get("Turbocharged");
        AeroKit aeroKit = AeroKitFactory.get("Standard Kit");

        assertThrows(IllegalArgumentException.class, () -> {
            Tyre invalidTyre = TyreFactory.get("InvalidTyre");
            new Car(engine, invalidTyre, aeroKit);
        });
    }

    @Test
    public void testAllEngineTypesCreated() {
        assertNotNull(EngineFactory.get("Standard"));
        assertNotNull(EngineFactory.get("Turbocharged"));
        assertNotNull(EngineFactory.get("Hybrid"));
    }

    @Test
    public void testHandlingScoreCalculations() {
        Engine engine = EngineFactory.get("Hybrid");
        Tyre tyre = TyreFactory.get("Medium");
        AeroKit aeroKit = AeroKitFactory.get("Standard Kit");

        Car car = new Car(engine, tyre, aeroKit);

        assertTrue(car.getHandlingScore() >= 0);
    }

    @Test
    public void testInvalidAeroKitTypeThrowsException() {
        Engine engine = EngineFactory.get("Standard");
        Tyre tyre = TyreFactory.get("Soft");

        assertThrows(IllegalArgumentException.class, () -> {
            AeroKit invalidAero = AeroKitFactory.get("InvalidKit");
            new Car(engine, tyre, invalidAero);
        });
    }


    @Test
    public void testCarTopSpeedEdgeCase() {
        Engine engine = EngineFactory.get("Hybrid");
        Tyre tyre = TyreFactory.get("Hard");
        AeroKit aeroKit = AeroKitFactory.get("High Downforce Kit");

        Car car = new Car(engine, tyre, aeroKit);
        assertTrue(car.getTopSpeed() >= 0);
    }

    @Test
    public void testFuelUsageCalculation() {
        Engine engine = EngineFactory.get("Turbocharged");
        Tyre tyre = TyreFactory.get("Medium");
        AeroKit aeroKit = AeroKitFactory.get("Standard Kit");

        Car car = new Car(engine, tyre, aeroKit);
        double fuelUsage = car.getFuelUsagePerKm();
        assertTrue(fuelUsage > 0 && fuelUsage < 100);
    }

    @Test
    public void testTyreWearRateWithinBounds() {
        Engine engine = EngineFactory.get("Hybrid");
        Tyre tyre = TyreFactory.get("Soft");
        AeroKit aeroKit = AeroKitFactory.get("Ground Effect Kit");

        Car car = new Car(engine, tyre, aeroKit);
        double wear = car.getTyreWearRate();
        assertTrue(wear >= 0);
    }

    @Test
    public void testSoftTyreHandling() {
        Engine engine = EngineFactory.get("Standard");
        Tyre tyre = TyreFactory.get("Soft");
        AeroKit aeroKit = AeroKitFactory.get("Standard Kit");

        Car car = new Car(engine, tyre, aeroKit);
        assertTrue(car.getHandlingScore() >= 0);
    }

    @Test
    public void testMultipleAeroKitsAvailable() {
        assertNotNull(AeroKitFactory.get("Standard Kit"));
        assertNotNull(AeroKitFactory.get("High Downforce Kit"));
        assertNotNull(AeroKitFactory.get("Ground Effect Kit"));
    }

    @Test
    public void testInvalidEngineTypeThrowsException() {
        Tyre tyre = TyreFactory.get("Hard");
        AeroKit aeroKit = AeroKitFactory.get("Standard Kit");

        assertThrows(IllegalArgumentException.class, () -> {
            Engine invalidEngine = EngineFactory.get("RocketEngine");
            new Car(invalidEngine, tyre, aeroKit);
        });
    }
}
