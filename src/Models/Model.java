package Models;

public class Model {

    private double startX;
    private double sizeX;
    private double endX;
    private double startY;
    private double sizeY;
    private double endY;
    private double price;
    private int imageID;
    private double priority;

    public Model(double startX, double sizeX, double startY, double sizeY, double price, int imageID, double priority) {
        this.startX = startX;
        this.sizeX = sizeX;
        this.endX = startX + sizeX;
        this.startY = startY;
        this.sizeY = sizeY;
        this.endY = startY + sizeY;
        this.price = price;
        this.imageID = imageID;
        this.priority = priority;
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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImageID() {
        return this.imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public double getPriority() {
        return this.priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "x=[" + startX + ", " + endX + "], y=[" + startY + ", " + endY + "], price=" + price + ", imageID=" +
                + imageID + ", priority=" + priority;
    }

}
