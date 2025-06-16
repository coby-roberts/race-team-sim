public class EngineFactory {
    public static Engine get(String choice) {
        return switch (choice.toLowerCase()) {
            case "standard" -> new Engine("Standard", 7.0, 350);
            case "turbocharged" -> new Engine("Turbocharged", 9.0, 450);
            default -> null;
        };
    }

    public static Engine createEngine(String choice) {
        return switch (choice.toLowerCase()) {
            case "standard" -> new Engine("Standard", 7.0, 350);
            case "turbocharged" -> new Engine("Turbocharged", 9.0, 450);
            default -> throw new IllegalArgumentException("Invalid engine type: " + choice);

        };
    }
}
