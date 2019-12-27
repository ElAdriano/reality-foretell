package Management;

public class Conditions {
    public final double budget;
    public final double amountOfPrisoners;
    public final double priceOfPrisonWard;
    public final double maxAmountOfPrisonersInPrisonWard;
    public final double priceOfSanitaryNook;
    public final double cameraRange;
    public final double amountOfGenerations;

    public final double aDimensionOfPrison;
    public final double bDimensionOfPrison;
    public final double cDimensionOfPrison;
    public final double dDimensionOfPrison;

    public Conditions(double budget,
                      double amountOfPrisoners,
                      double priceOfPrisonWard,
                      double maxAmountOfPrisonersInPrisonWard,
                      double priceOfSanitaryNook,
                      double cameraRange,
                      double amountOfGenerations){
        this.budget = budget;
        this.amountOfPrisoners = amountOfPrisoners;
        this.priceOfPrisonWard = priceOfPrisonWard;
        this.maxAmountOfPrisonersInPrisonWard = maxAmountOfPrisonersInPrisonWard;
        this.priceOfSanitaryNook = priceOfSanitaryNook;
        this.cameraRange = cameraRange;
        this.amountOfGenerations = amountOfGenerations;

        this.aDimensionOfPrison = 0;
        this.bDimensionOfPrison = 0;
        this.cDimensionOfPrison = 0;
        this.dDimensionOfPrison = 0;
    }
}
