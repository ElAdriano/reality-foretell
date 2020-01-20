package Models;

public class PrisonWard extends ComplexModel {

    private int sanitaryNookPosition;

    public PrisonWard(double startX, double sizeX, double startY, double sizeY) {
        super(startX, sizeX, startY, sizeY, 100000, 1);
    }

    public int getSanitaryNookPosition() {
        return sanitaryNookPosition;
    }

    public void setSanitaryNookPosition(int sanitaryNookPosition) {
        this.sanitaryNookPosition = sanitaryNookPosition;
    }

}
