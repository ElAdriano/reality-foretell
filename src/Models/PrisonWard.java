package Models;

public class PrisonWard extends ComplexModel{

    private int sanitaryNookPosition;
    private static double priority = 1;
    private static int price = 100000;

    public PrisonWard(double startX, double sizeX, double startY, double sizeY){
        super(startX, sizeX, startY, sizeY);
    }

    public int getSanitaryNookPosition() {
        return sanitaryNookPosition;
    }

    public void setSanitaryNookPosition(int sanitaryNookPosition) {
        this.sanitaryNookPosition = sanitaryNookPosition;
    }

    public static double getPriority() {
        return priority;
    }

    public static int getPrice() {
        return price;
    }

}
