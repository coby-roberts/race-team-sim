import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // === Step 1: Track Details ===
        System.out.println("=== Enter Track Details ===");

        System.out.print("Track Length (km): ");
        double trackLength = getPositiveDouble(sc);

        String trackType;
        while (true) {
            System.out.print("Track Type (circuit/street/off-road): ");
            trackType = sc.nextLine().trim().toLowerCase();
            if (!trackType.isEmpty()) break;
            System.out.println("Please enter a valid track type.");
        }

        String weather;
        while (true) {
            System.out.print("Weather (dry/wet): ");
            weather = sc.nextLine().trim().toLowerCase();
            if (weather.equals("dry") || weather.equals("wet")) break;
            System.out.println("Invalid weather. Please enter 'dry' or 'wet'.");
        }

        System.out.print("Fuel Tank Capacity (liters): ");
        double fuelCapacity = getPositiveDouble(sc);

        // === Step 2: Car Configuration ===
        System.out.println("\n=== Configure Your Car ===");

        Engine engine = null;
        while (engine == null) {
            System.out.print("Select Engine (Standard/Turbocharged): ");
            engine = EngineFactory.get(sc.nextLine().trim());
            if (engine == null) System.out.println("Invalid engine choice. Try again.");
        }

        Tyre tyre = null;
        while (tyre == null) {
            System.out.print("Select Tyre (Soft/Medium/Hard): ");
            tyre = TyreFactory.get(sc.nextLine().trim());
            if (tyre == null) System.out.println("Invalid tyre choice. Try again.");
        }

        AeroKit aeroKit = null;
        while (aeroKit == null) {
            AeroKitFactory.listAvailableKits();
            System.out.print("Select Aero Kit (enter exact name): ");
            aeroKit = AeroKitFactory.get(sc.nextLine().trim());
            if (aeroKit == null) System.out.println("Invalid aero kit choice. Try again.");
        }

        Car car = new Car(engine, tyre, aeroKit);

        System.out.println("\n=== Car Configured ===\n" + car);

        // === Step 3: Strategy ===
        System.out.println("\n=== Race Strategy ===");
        generateStrategy(car, trackLength, weather, fuelCapacity);

        sc.close();
    }

    private static double getPositiveDouble(Scanner sc) {
        while (true) {
            try {
                double val = Double.parseDouble(sc.nextLine());
                if (val > 0) return val;
                System.out.print("Please enter a positive number: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }

    private static void generateStrategy(Car car, double trackLength, String weather, double fuelCapacity) {
        double fuelConsumptionRate = car.getFuelUsagePerKm();
        double totalFuelNeeded = fuelConsumptionRate * trackLength;

        System.out.printf("Estimated Fuel Consumption Rate: %.4f liters/km%n", fuelConsumptionRate);
        System.out.printf("Total Fuel Needed: %.2f liters%n", totalFuelNeeded);

        int fuelPitStops = (int) Math.ceil(totalFuelNeeded / fuelCapacity) - 1;
        if (fuelPitStops < 0) fuelPitStops = 0;
        System.out.printf("Calculated Pit Stops Needed (fuel): %d%n", fuelPitStops);

        int tyrePitStops = (int) Math.ceil(trackLength / car.tyre.durability) - 1;
        if (tyrePitStops < 0) tyrePitStops = 0;
        System.out.printf("Tyre durability allows for %d km per set.%n", car.tyre.durability);
        System.out.printf("Calculated Pit Stops Needed (tyres): %d%n", tyrePitStops);

        int totalPitStops = Math.max(fuelPitStops, tyrePitStops);
        System.out.printf("Total Pit Stops Needed: %d%n", totalPitStops);

        boolean weatherMatchesTyre = matchesWeather(car.tyre.type, weather);
        String tyreWearMsg = weatherMatchesTyre
                ? "Tyre choice suits current weather."
                : "Tyre choice may cause faster wear due to weather mismatch.";
        System.out.println(tyreWearMsg);

        if (totalPitStops > 0 && !weatherMatchesTyre) {
            System.out.println("Strategy Suggestion: Change tyres at each pit stop to match weather.");
        } else if (!weatherMatchesTyre) {
            System.out.println("Strategy Suggestion: Consider starting with weather-appropriate tyres.");
        } else {
            System.out.println("Strategy Suggestion: Maintain current tyres throughout the race.");
        }

        System.out.println("\nPit Stop Strategy:");
        if (totalPitStops == 0) {
            System.out.println("No pit stops required for fuel or tyres.");
        } else {
            for (int i = 1; i <= totalPitStops; i++) {
                double pitKm = (trackLength / (totalPitStops + 1)) * i;
                System.out.printf("- Pit stop %d at %.2f km%n", i, pitKm);
                if (i <= fuelPitStops) {
                    System.out.println("  Refuel.");
                }
                if (i <= tyrePitStops) {
                    System.out.println("  Change tyres.");
                }
            }
        }
    }

    private static boolean matchesWeather(Tyre.TyreType tyreType, String weather) {
        return switch (weather) {
            case "dry" -> tyreType == Tyre.TyreType.SOFT || tyreType == Tyre.TyreType.MEDIUM;
            case "wet" -> tyreType == Tyre.TyreType.HARD;
            default -> true;
        };
    }
}