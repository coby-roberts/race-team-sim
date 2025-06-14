public class AeroKit {
    public String name;
    public double dragCoefficient;
    public int downforceKg;
    public int topSpeedKmh;
    public double fuelEfficiencyKmpl;
    public int corneringRating;

    public AeroKit(String name, double dragCoefficient, int downforceKg, int topSpeedKmh,
                   double fuelEfficiencyKmpl, int corneringRating) {
        this.name = name;
        this.dragCoefficient = dragCoefficient;
        this.downforceKg = downforceKg;
        this.topSpeedKmh = topSpeedKmh;
        this.fuelEfficiencyKmpl = fuelEfficiencyKmpl;
        this.corneringRating = corneringRating;
    }

    @Override
    public String toString() {
        return String.format("%s: Drag=%.2f, Downforce=%dkg, Top Speed=%dkm/h, Efficiency=%.1fkm/l, Cornering=%d",
                name, dragCoefficient, downforceKg, topSpeedKmh, fuelEfficiencyKmpl, corneringRating);
    }
}
