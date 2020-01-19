package Management;

public class Conditions {
    public double budget;
    public int amountOfPrisoners;
    public double priceOfPrisonWard;
    public int maxAmountOfPrisonersInPrisonWard;
    public double priceOfSanitaryNook;
    public double cameraRange;
    public int amountOfGenerations;

    public double aDimensionOfPrison;
    public double bDimensionOfPrison;
    public double cDimensionOfPrison;
    public double dDimensionOfPrison;

    public int doorSize;
    public int xMinSizeOfMonitoringRoom;
    public int yMinSizeOfMonitoringRoom;
    public int minSizeOfWard;
    public int sizeOfSanitaryNook;

    public Conditions() {
        budget = -1;
        amountOfPrisoners = -1;
        priceOfPrisonWard = -1;
        maxAmountOfPrisonersInPrisonWard = -1;
        priceOfSanitaryNook = -1;
        cameraRange = -1;
        amountOfGenerations = -1;

        aDimensionOfPrison = -1;
        bDimensionOfPrison = -1;
        cDimensionOfPrison = -1;
        dDimensionOfPrison = -1;

        doorSize = 3;
        xMinSizeOfMonitoringRoom = 10;
        yMinSizeOfMonitoringRoom = 10;
        minSizeOfWard = 10;
        sizeOfSanitaryNook = 5;
    }
}
