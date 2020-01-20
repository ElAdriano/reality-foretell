package Models;

public class Model {

    private static int price;
    private static double priority;
    private double startX;
    private double sizeX;
    private double endX;
    private double startY;
    private double sizeY;
    private double endY;

    public Model(double startX, double sizeX, double startY, double sizeY, int price, double priority) {
        this.startX = startX;
        this.sizeX = sizeX;
        this.endX = startX + sizeX;
        this.startY = startY;
        this.sizeY = sizeY;
        this.endY = startY + sizeY;
        this.price = price;
        this.priority = priority;
    }

    public static int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public void rotate() {
        double tmpSize = sizeX;
        sizeX = sizeY;
        sizeY = tmpSize;
        endX = startX + sizeX;
        endY = startY + sizeY;
    }

    public double getStartX() {
        return this.startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
        this.endX = startX + this.sizeX;
    }

    public double getSizeX() {
        return this.sizeX;
    }

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
        this.endX = this.startX + sizeX;
    }

    public double getEndX() {
        return this.endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
        this.startX = this.endX - this.sizeX;
    }

    public double getStartY() {
        return this.startY;
    }

    public void setStartY(double startY) {
        this.startY = startY;
        this.endY = startY + this.sizeY;
    }

    public double getSizeY() {
        return this.sizeY;
    }

    public void setSizeY(double sizeY) {
        this.sizeY = sizeY;
        this.endY = this.startY + sizeY;
    }

    public double getEndY() {
        return this.endY;
    }

    public void setEndY(double endY) {
        this.endY = endY;
        this.startY = this.endY - this.sizeY;
    }

}
