public class AeroKitFactory {
    public static AeroKit get(String name) {
        return switch (name.toLowerCase()) {
            case "standard kit" -> new AeroKit("Standard Kit", 0.30, 200, 250, 12, 6);
            case "downforce-focussed kit" -> new AeroKit("Downforce-Focussed Kit", 0.35, 350, 220, 10, 9);
            case "drag reduction system kit" -> new AeroKit("Drag Reduction System Kit", 0.25, 200, 290, 13, 6);
            case "wet weather kit" -> new AeroKit("Wet Weather Kit", 0.32, 220, 230, 11, 7);
            case "extreme aero kit" -> new AeroKit("Extreme Aero Kit", 0.40, 500, 200, 9, 10);
            default -> null;
        };
    }

    public static void listAvailableKits() {
        System.out.println("""
            Available Aerodynamic Kits:
            1. Standard Kit
            2. Downforce-Focussed Kit
            3. Drag Reduction System Kit
            4. Wet Weather Kit
            5. Extreme Aero Kit
            """);
    }

    public static AeroKit createAeroKit(String name) {
        return switch (name.toLowerCase()) {
            case "standard kit" -> new AeroKit("Standard Kit", 0.30, 200, 250, 12, 6);
            case "downforce-focussed kit" -> new AeroKit("Downforce-Focussed Kit", 0.35, 350, 220, 10, 9);
            case "drag reduction system kit" -> new AeroKit("Drag Reduction System Kit", 0.25, 200, 290, 13, 6);
            case "wet weather kit" -> new AeroKit("Wet Weather Kit", 0.32, 220, 230, 11, 7);
            case "extreme aero kit" -> new AeroKit("Extreme Aero Kit", 0.40, 500, 200, 9, 10);
            default -> throw new IllegalArgumentException("Invalid AeroKit type: " + name);

        };
    }
}
