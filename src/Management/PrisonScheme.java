package Management;

import Models.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class PrisonScheme {
    private ArrayList<PrisonWard> prisonWards;
    private ArrayList<Door> doorsOnScheme;
    private ArrayList<LightBulb> lightBulbsOnScheme;
    private ArrayList<Camera> camerasOnScheme;
    private ArrayList<Window> windowsOnScheme;

    private MonitoringRoom monitoringRoom;
    private SanitaryNook sanitaryNook;

    private Image imageToShow;
    private Fields[][] prisonPlan;
    private int planSquareSize;

    private int aWall;
    private int bWall;
    private int cWall;
    private int dWall;

    private int x1Coridor;
    private int x2Coridor;
    private int y1Coridor;
    private int y2Coridor;

    private int rMonitorRoomPlacement;

    private Random rand;

    private double rate;

    public PrisonScheme() {
        this.camerasOnScheme = new ArrayList<>();
        this.doorsOnScheme = new ArrayList<>();
        this.lightBulbsOnScheme = new ArrayList<>();
        this.camerasOnScheme = new ArrayList<>();
        this.windowsOnScheme = new ArrayList<>();

        this.planSquareSize = (int) Math.max(SchemeGenerator.conditions.bDimensionOfPrison, SchemeGenerator.conditions.aDimensionOfPrison);

        prisonPlan = new Fields[planSquareSize][planSquareSize];

        this.aWall = (int) SchemeGenerator.conditions.aDimensionOfPrison;
        this.bWall = (int) SchemeGenerator.conditions.bDimensionOfPrison;
        this.cWall = (int) SchemeGenerator.conditions.cDimensionOfPrison;
        this.dWall = (int) SchemeGenerator.conditions.dDimensionOfPrison;

        this.rand = new Random();

        fillAsOutsideArea();
        fillAsEmpty();

        //TODO create PrisonScheme instance

        addOutsideWalls();
        addCorridors();
        addMonitoringRoom();
    }


    private void fillAsOutsideArea() {
        for (int w = 0; w < planSquareSize; w++) {
            for (int h = 0; h < planSquareSize; h++) {
                prisonPlan[w][h] = Fields.OUTSIDE_FIELD;
            }
        }
    }

    private void fillAsEmpty() {
        for (int w = 0; w < planSquareSize; w++) {
            for (int h = 0; h < planSquareSize; h++) {
                if (!(w > bWall - dWall - 1 && h < cWall - 1)) {
                    prisonPlan[w][h] = Fields.EMPTY;
                }
            }
        }
    }

    private void addOutsideWalls() {
        for (int w = 0; w < bWall; w++) {
            for (int h = 0; h < aWall; h++) {
                if (w == 0 || h == aWall-1 ||
                        (h == 0 &&  w <= bWall- dWall-1) ||
                        (h <= cWall-1 && w == bWall - dWall - 1) ||
                        (h == cWall-1 && w >= bWall - dWall - 1) ||
                        (h >= cWall-1 && w == bWall-1)) {
                    prisonPlan[w][h] = Fields.WALL;
                }
            }
        }
    }

    private void addCorridors() {
        int xMR = SchemeGenerator.conditions.xMinSizeOfMonitoringRoom;
        int yMR = SchemeGenerator.conditions.yMinSizeOfMonitoringRoom;
        x1Coridor = rand.nextInt((bWall - dWall)*2/3-xMR)+xMR;
        x2Coridor = (bWall-dWall-x1Coridor)/2 + x1Coridor;
        y1Coridor = rand.nextInt((aWall- cWall)*2/3-yMR)+yMR;
        y2Coridor = (aWall - cWall - y1Coridor)/2 + y1Coridor;
        for (int w = 1; w < bWall-1; w++) {
            for (int h = 1; h < aWall-1; h++) {
                if ( h == aWall - y1Coridor || w == x1Coridor || h == aWall - y2Coridor || w == x2Coridor) {
                    prisonPlan[w][h] = Fields.CORRIDOR;
                }
            }
        }
    }

    private void addMonitoringRoom() {

        rMonitorRoomPlacement = rand.nextInt(4) + 1;

        switch (rMonitorRoomPlacement) {
            case 1:
                for (int w = 1; w < bWall-1; w++) {
                    for (int h = 1; h < aWall-1; h++) {
                        if (  h < aWall - y1Coridor && w < x1Coridor && h > aWall - y2Coridor ) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;
                        }
                    }
                }
                break;
            case 2:
                for (int w = 1; w < bWall-1; w++) {
                    for (int h = 1; h < aWall-1; h++) {
                        if (  h < aWall - y1Coridor && w > x1Coridor && h > aWall - y2Coridor && w < x2Coridor) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;
                        }
                    }
                }
                break;
            case 3:
                for (int w = 1; w < bWall-1; w++) {
                    for (int h = 1; h < aWall-1; h++) {
                        if ( w < x1Coridor && h > aWall - y1Coridor ) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;
                        }
                    }
                }
                break;
            case 4:
                for (int w = 1; w < bWall-1; w++) {
                    for (int h = 1; h < aWall-1; h++) {
                        if ( w > x1Coridor && h > aWall - y1Coridor && w < x2Coridor) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;
                        }
                    }
                }
                break;
        }
    }

    private void createImageToShow() {
        imageToShow = ImageCreator.createImage(prisonPlan, planSquareSize, planSquareSize);
    }

    public void arrangePrisonScheme() {
        //TODO initializing PrisonScheme components and whole prison
    }

    public void updateImage(Image image) {
        imageToShow = image;
    }

    public Image getImageToShow() {
        return imageToShow;
    }

    public int getPlanSquareSize() {
        return planSquareSize;
    }

    public double getRate() {
        return rate;
    }

    public Fields[][] getPrisonPlan() {
        return prisonPlan;
    }
}
