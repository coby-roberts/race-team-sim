import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarComponentTests {

    @Test
    public void testValidCarConfiguration() {
        Engine engine = EngineFactory.createEngine("Turbocharged");
        Tyre tyre = TyreFactory.createTyre("Soft");
        AeroKit aeroKit = AeroKitFactory.createAeroKit("Ground Effect Kit");

        Car car = new Car(engine, tyre, aeroKit);

        assertEquals("Turbocharged", car.getEngine().getType());
        assertEquals("Soft", car.getTyre().getType());
        assertEquals("Ground Effect Kit", car.getAeroKit().getType());
    }

    @Test
    public void testCarPerformanceMetrics() {
        Engine engine = EngineFactory.createEngine("Standard");
        Tyre tyre = TyreFactory.createTyre("Medium");
        AeroKit aeroKit = AeroKitFactory.createAeroKit("Standard Kit");

        Car car = new Car(engine, tyre, aeroKit);

        assertTrue(car.getTopSpeed() > 0);
        assertTrue(car.getFuelEfficiency() > 0);
    }

    @Test
    public void testNullEngineThrowsException() {
        Tyre tyre = TyreFactory.createTyre("Hard");
        AeroKit aeroKit = AeroKitFactory.createAeroKit("Standard Kit");

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(null, tyre, aeroKit);
        });
    }

    @Test
    public void testIncompatibleTyreThrowsException() {
        Engine engine = EngineFactory.createEngine("Turbocharged");
        AeroKit aeroKit = AeroKitFactory.createAeroKit("Standard Kit");

        assertThrows(IllegalArgumentException.class, () -> {
            Tyre invalidTyre = TyreFactory.createTyre("InvalidTyre");
            new Car(engine, invalidTyre, aeroKit);
        });
    }

    @Test
    public void testAllEngineTypesCreated() {
        assertNotNull(EngineFactory.createEngine("Standard"));
        assertNotNull(EngineFactory.createEngine("Turbocharged"));
        assertNotNull(EngineFactory.createEngine("Hybrid"));
    }

    @Test
    public void testRaceFuelCalculation() {
        RaceConditions rc = new RaceConditions("Dry", 5.0, 60);
        Car car = new Car(EngineFactory.createEngine("Standard"),
                TyreFactory.createTyre("Soft"),
                AeroKitFactory.createAeroKit("Standard Kit"));

        StrategyOptimizer optimizer = new StrategyOptimizer();
        double fuel = optimizer.calculateFuelUsage(car, rc);
        assertTrue(fuel > 0);
    }

    @Test
    public void testPitStopCount() {
        RaceConditions rc = new RaceConditions("Dry", 10.0, 300);
        Car car = new Car(EngineFactory.createEngine("Hybrid"),
                TyreFactory.createTyre("Hard"),
                AeroKitFactory.createAeroKit("Ground Effect Kit"));

        StrategyOptimizer optimizer = new StrategyOptimizer();
        int stops = optimizer.estimatePitStops(car, rc);
        assertTrue(stops >= 0);
    }

    @Test
    public void testTyreSelectionWetConditions() {
        RaceConditions rc = new RaceConditions("Wet", 6.0, 100);
        StrategyOptimizer optimizer = new StrategyOptimizer();

        String recommendedTyre = optimizer.recommendTyre(rc);
        assertEquals("Wet", recommendedTyre);
    }

    @Test
    public void testNegativeFuelCapacityThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RaceConditions("Dry", -5.0, 60);
        });
    }

    @Test
    public void testNullRaceConditionsHandled() {
        Car car = new Car(EngineFactory.createEngine("Standard"),
                TyreFactory.createTyre("Medium"),
                AeroKitFactory.createAeroKit("Standard Kit"));

        StrategyOptimizer optimizer = new StrategyOptimizer();
        assertThrows(NullPointerException.class, () -> {
            optimizer.calculateFuelUsage(car, null);
        });
    }

    @Test
    public void testZeroLapsReturnsZeroFuel() {
        RaceConditions rc = new RaceConditions("Dry", 5.0, 0);
        Car car = new Car(EngineFactory.createEngine("Standard"),
                TyreFactory.createTyre("Medium"),
                AeroKitFactory.createAeroKit("Standard Kit"));

        StrategyOptimizer optimizer = new StrategyOptimizer();
        double fuel = optimizer.calculateFuelUsage(car, rc);
        assertEquals(0.0, fuel);
    }

    @Test
    public void testExtremeAeroKitDownforce() {
        AeroKit aeroKit = AeroKitFactory.createAeroKit("Extreme Aero Kit");
        assertTrue(aeroKit.getDownforce() >= 500);
    }

    @Test
    public void testRepeatabilityOfStrategy() {
        RaceConditions rc = new RaceConditions("Dry", 8.0, 100);
        Car car = new Car(EngineFactory.createEngine("Standard"),
                TyreFactory.createTyre("Medium"),
                AeroKitFactory.createAeroKit("Hybrid Kit"));
        StrategyOptimizer optimizer = new StrategyOptimizer();

        double firstRun = optimizer.calculateFuelUsage(car, rc);
        double secondRun = optimizer.calculateFuelUsage(car, rc);

        assertEquals(firstRun, secondRun);
    }

    @Test
    public void testHybridKitAttributesRange() {
        AeroKit aeroKit = AeroKitFactory.createAeroKit("Hybrid Kit");

        assertTrue(aeroKit.getTopSpeed() >= 250 && aeroKit.getTopSpeed() <= 270);
        assertTrue(aeroKit.getFuelEfficiency() >= 11 && aeroKit.getFuelEfficiency() <= 13);
    }

    @Test
    public void testTyreWearCalculation() {
        RaceConditions rc = new RaceConditions("Dry", 6.0, 120);
        Car car = new Car(EngineFactory.createEngine("Turbocharged"),
                TyreFactory.createTyre("Soft"),
                AeroKitFactory.createAeroKit("Low-Drag Kit"));

        StrategyOptimizer optimizer = new StrategyOptimizer();
        double wear = optimizer.estimateTyreWear(car, rc);
        assertTrue(wear > 0);
    }
}
