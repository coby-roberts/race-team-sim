public class TyreFactory {

    public static Tyre get(String choice) {
        return switch (choice.toLowerCase()) {
            case "soft" -> new Tyre(Tyre.TyreType.SOFT, 100, 0.95);
            case "medium" -> new Tyre(Tyre.TyreType.MEDIUM, 150, 0.85);
            case "hard" -> new Tyre(Tyre.TyreType.HARD, 200, 0.75);
            default -> throw new IllegalArgumentException("Invalid tyre type: " + choice);
        };
    }

    public static Tyre createTyre(String choice) {
        return switch (choice.toLowerCase()) {
            case "soft" -> new Tyre(Tyre.TyreType.SOFT, 100, 0.95);
            case "medium" -> new Tyre(Tyre.TyreType.MEDIUM, 150, 0.85);
            case "hard" -> new Tyre(Tyre.TyreType.HARD, 200, 0.75);
            default -> throw new IllegalArgumentException("Invalid tyre type: " + choice);
        };
    }
}
