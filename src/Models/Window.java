package Models;

public class Window extends Model {

    private static int price = 500;
    private static double priority = 0.25;

    public Window(double startX, double SizeX, double startY, double SizeY) {
        super(startX, SizeX, startY, SizeY);
    }

    public static double getPriority() {
        return priority;
    }

    public static int getPrice() {
        return price;
    }

}
