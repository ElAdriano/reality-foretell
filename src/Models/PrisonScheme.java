package Models;

import Management.Fields;
import Management.SchemeGenerator;

import java.util.ArrayList;
import java.util.Random;

public class PrisonScheme {

    private ArrayList<PrisonWard> prisonWardsOnScheme;
    private ArrayList<SanitaryNook> sanitaryNooksOnScheme;
    private ArrayList<Door> doorsOnScheme;
    private ArrayList<LightBulb> lightBulbsOnScheme;
    private ArrayList<Camera> camerasOnScheme;
    private ArrayList<Window> windowsOnScheme;

    private MonitoringRoom monitoringRoom;
    private SanitaryNook sanitaryNook;

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

    private int w1;
    private int w2;
    private int w3;
    private int w4;

    private int w1Size;
    private int w2Size;
    private int w3Size;
    private int w4Size;

    private int rMonitorRoomPlacement;
    private int extraCorridor;

    private Random rand;

    private double rate;
    private double price;

    public PrisonScheme() {
        this.prisonWardsOnScheme = new ArrayList<>();
        this.sanitaryNooksOnScheme = new ArrayList<>();
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
        addInsideWalls();
        addMonitoringRoom();
        addEntranceDoor();
        addWards();
        fillWards();

        calculatePriceOfScheme();
    }

    private void calculatePriceOfScheme(){
        price = 0;
        price += prisonWardsOnScheme.size() * PrisonWard.price;
        price += sanitaryNooksOnScheme.size() * SanitaryNook.price;
        price += doorsOnScheme.size() * Door.price;
        price += lightBulbsOnScheme.size() * LightBulb.price;
        price += camerasOnScheme.size() * Camera.price;
        price += windowsOnScheme.size() * Window.price;
        price += MonitoringRoom.price;
    }

    private void fillAsOutsideArea() {
        for (int w = 0; w < planSquareSize; w++) {
            for (int h = 0; h < planSquareSize; h++) {
                prisonPlan[w][h] = Fields.OUTSIDE_FIELD;
            }
        }
    }

    private void fillAsEmpty() {
        for (int w = 0; w < bWall; w++) {
            for (int h = 0; h < aWall; h++) {
                if (!(w > bWall - dWall - 1 && h < cWall - 1)) {
                    prisonPlan[w][h] = Fields.EMPTY;
                }
            }
        }
    }

    private void addOutsideWalls() {
        for (int w = 0; w < bWall; w++) {
            for (int h = 0; h < aWall; h++) {
                if (w == 0 || h == aWall - 1 ||
                        (h == 0 && w <= bWall - dWall) ||
                        (h <= cWall && w == bWall - dWall) ||
                        (h == cWall - 1 && w >= bWall - dWall) ||
                        (h >= cWall && w == bWall - 1)) {
                    prisonPlan[w][h] = Fields.WALL;
                }
            }
        }
    }

    private void addCorridors() {
        int xMR = SchemeGenerator.conditions.xMinSizeOfMonitoringRoom;
        int yMR = SchemeGenerator.conditions.yMinSizeOfMonitoringRoom;
        x1Coridor = rand.nextInt((bWall - dWall) * 2 / 3 - xMR) + xMR;
        x2Coridor = (bWall - dWall - x1Coridor) / 2 + x1Coridor;
        y1Coridor = rand.nextInt((aWall - cWall) * 2 / 3 - yMR) + yMR;
        y2Coridor = (aWall - cWall - y1Coridor) / 2 + y1Coridor;
        for (int w = 1; w < bWall - 1; w++) {
            for (int h = 1; h < aWall - 1; h++) {
                if ((h < aWall - y1Coridor && w > x1Coridor && h > aWall - y2Coridor) || (w > x1Coridor && w < x2Coridor && h < aWall - y1Coridor)) {
                    prisonPlan[w][h] = Fields.CORRIDOR;
                }
            }
        }
    }

    private void addInsideWalls() {
        for (int w = 1; w < bWall - 1; w++) {
            for (int h = 1; h < aWall - 1; h++) {
                if ((w == x1Coridor && h < aWall - y1Coridor) ||
                        (w == x2Coridor && h <= aWall - y2Coridor) ||
                        (w >= x2Coridor && h == aWall - y2Coridor) ||
                        (w >= x1Coridor && h == aWall - y1Coridor) ||
                        (w <= x1Coridor && h == aWall - y1Coridor) ||
                        (w <= x1Coridor && h == aWall - y2Coridor) ||
                        (w == x1Coridor && h >= aWall - y1Coridor) ||
                        (w == x2Coridor && h >= aWall - y1Coridor) ||
                        (w >= x2Coridor && h == cWall - 1) ||
                        (w == bWall - dWall && h <= aWall - y2Coridor)) {
                    prisonPlan[w][h] = Fields.WALL;
                }
            }
        }
    }

    private void addMonitoringRoom() {

        rMonitorRoomPlacement = rand.nextInt(4) + 1;

        switch (rMonitorRoomPlacement) {
            case 1:
                for (int w = 1; w < bWall - 1; w++) {
                    for (int h = 1; h < aWall - 1; h++) {
                        if (h < aWall - y1Coridor && w < x1Coridor && h > aWall - y2Coridor) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;

                        }
                    }
                }
                monitoringRoom = new MonitoringRoom(1, x1Coridor - 2, aWall - y2Coridor + 1, y2Coridor - y1Coridor - 2);
                extraCorridor = 1;
                addExtraWards(2);
                addExtraWards(3);
                break;
            case 2:
                for (int w = 1; w < bWall - 1; w++) {
                    for (int h = 1; h < aWall - 1; h++) {
                        if (h >= cWall && w < bWall - dWall && h < aWall - y2Coridor && w > x2Coridor) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;
                        }
                    }
                }
                monitoringRoom = new MonitoringRoom(x2Coridor + 1, bWall - dWall - x2Coridor - 2, cWall + 1, aWall - cWall - y2Coridor - 2);
                extraCorridor = rand.nextInt(2) * 3 + 1;
                addExtraWards(3);
                addExtraWards(extraCorridor);
                break;
            case 3:
                for (int w = 1; w < bWall - 1; w++) {
                    for (int h = 1; h < aWall - 1; h++) {
                        if (w < x1Coridor && h > aWall - y1Coridor) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;
                        }
                    }
                }
                monitoringRoom = new MonitoringRoom(1, x1Coridor - 2, aWall - y1Coridor + 1, y1Coridor - 2);
                extraCorridor = rand.nextInt(2) * 3 + 1;
                addExtraWards(2);
                addExtraWards(extraCorridor);
                break;
            case 4:
                for (int w = 1; w < bWall - 1; w++) {
                    for (int h = 1; h < aWall - 1; h++) {
                        if (w > x1Coridor && h > aWall - y1Coridor && w < x2Coridor) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;
                        }
                    }
                }
                monitoringRoom = new MonitoringRoom(x1Coridor + 1, x2Coridor - x1Coridor - 2, aWall - y1Coridor + 1, y1Coridor - 2);
                extraCorridor = 4;
                addExtraWards(2);
                addExtraWards(3);
                break;
        }

        addExtraCorridor(extraCorridor);
    }

    private void addExtraCorridor(int a) {
        switch (a) {
            case 1:
                for (int w = 1; w < bWall - 1; w++) {
                    for (int h = 1; h < aWall - 1; h++) {
                        if (w > x1Coridor && h >= aWall - y1Coridor && w < x2Coridor) {
                            prisonPlan[w][h] = Fields.CORRIDOR;
                        }
                    }
                }
                break;
            case 4:
                for (int w = 1; w < bWall - 1; w++) {
                    for (int h = 1; h < aWall - 1; h++) {
                        if (h < aWall - y1Coridor && w <= x1Coridor && h > aWall - y2Coridor) {
                            prisonPlan[w][h] = Fields.CORRIDOR;
                        }
                    }
                }
                break;
        }
    }

    private void addExtraWards(int a) {
        switch (a) {
            case 1:
                prisonWardsOnScheme.add(new PrisonWard(1, x1Coridor - 2, aWall - y2Coridor + 1, y2Coridor - y1Coridor - 2));
                break;
            case 2:
                prisonWardsOnScheme.add(new PrisonWard(x2Coridor + 1, bWall - dWall - x2Coridor - 2, cWall, aWall - cWall - y2Coridor - 1));
                break;
            case 3:
                prisonWardsOnScheme.add(new PrisonWard(1, x1Coridor - 2, aWall - y1Coridor + 1, y1Coridor - 3));
                break;
            case 4:
                prisonWardsOnScheme.add(new PrisonWard(x1Coridor + 1, x2Coridor - x1Coridor - 2, aWall - y1Coridor + 1, y1Coridor - 3));
                break;
        }
    }

    private void addEntranceDoor() {

        int a = rand.nextInt(3) + 1;
        int dS = SchemeGenerator.conditions.doorSize;
        switch (a) {
            case 1:
                for (int i = 0; i < dS; i++) {
                    prisonPlan[(x2Coridor + x1Coridor - dS) / 2 + i][0] = Fields.DOOR;
                }
                break;
            case 2:
                for (int i = 0; i < dS; i++) {
                    prisonPlan[bWall - 1][aWall - (y1Coridor + y2Coridor + dS) / 2 + i] = Fields.DOOR;
                }
                break;
            case 3:
                switch (extraCorridor) {
                    case 2:
                        for (int i = 0; i < dS; i++) {
                            prisonPlan[0][aWall - (y1Coridor + y2Coridor + dS) / 2 + i] = Fields.DOOR;
                        }
                        break;
                    case 1:
                        for (int i = 0; i < dS; i++) {
                            prisonPlan[(x2Coridor + x1Coridor - dS) / 2 + i][aWall - 1] = Fields.DOOR;
                        }
                        break;
                }
                break;
        }
    }

    private void addWards() {

        int minSize = SchemeGenerator.conditions.minSizeOfWard;
        int z;

        switch (1) {
            case 1:
                w1 = rand.nextInt((aWall - y2Coridor) / minSize - 1) + 2;
                w1Size = (aWall - y2Coridor - 1) / w1;
                z = 1;
                prisonWardsOnScheme.add(new PrisonWard(1, x1Coridor - 2, 1, w1Size - 2));
                while (z < w1) {
                    for (int w = 1; w < x1Coridor; w++) {
                        for (int h = 1; h < aWall - y2Coridor; h++) {
                            if (h == z * w1Size) {
                                prisonPlan[w][h] = Fields.WALL;
                            }
                        }
                    }
                    prisonWardsOnScheme.add(new PrisonWard(1, x1Coridor - 2, 1 + z * (w1Size), w1Size - 2));
                    z++;
                }
            case 2:
                w2 = rand.nextInt((cWall) / minSize - 1) + 2;
                w2Size = (cWall - 1) / w2;
                z = 1;
                prisonWardsOnScheme.add(new PrisonWard(x2Coridor + 1, bWall - dWall - x2Coridor - 2, 1, w2Size - 2));
                while (z < w2) {
                    for (int w = x2Coridor + 1; w < bWall - dWall; w++) {
                        for (int h = 1; h < cWall; h++) {
                            if (h == z * w2Size) {
                                prisonPlan[w][h] = Fields.WALL;
                            }
                        }
                    }
                    prisonWardsOnScheme.add(new PrisonWard(x2Coridor + 1, bWall - dWall - x2Coridor - 2, 1 + z * (w2Size), w2Size - 2));
                    z++;
                }
            case 3:
                w3 = rand.nextInt((dWall) / minSize - 1) + 2;
                w3Size = (dWall - 1) / w3;
                z = 1;
                prisonWardsOnScheme.add(new PrisonWard(bWall - dWall + 1, w3Size - 2, cWall, aWall - cWall - y2Coridor - 1));
                while (z < w3) {
                    for (int w = bWall - dWall; w < bWall; w++) {
                        for (int h = cWall + 1; h < aWall - y2Coridor; h++) {
                            if (w == bWall - dWall + z * w3Size) {
                                prisonPlan[w][h] = Fields.WALL;
                            }
                        }
                    }
                    prisonWardsOnScheme.add(new PrisonWard(bWall - dWall + 1 + z * w3Size, w3Size - 2, cWall, aWall - cWall - y2Coridor - 1));
                    z++;
                }
            case 4:
                w4 = rand.nextInt((bWall - x2Coridor) / minSize - 1) + 2;
                w4Size = (bWall - x2Coridor - 1) / w4;
                z = 1;
                prisonWardsOnScheme.add(new PrisonWard(x2Coridor + 1, w4Size - 2, aWall - y1Coridor + 1, y1Coridor - 3));
                while (z < w4) {
                    for (int w = x2Coridor + 1; w < bWall; w++) {
                        for (int h = aWall - y1Coridor + 1; h < aWall; h++) {
                            if (w == x2Coridor + z * w4Size) {
                                prisonPlan[w][h] = Fields.WALL;
                            }
                        }
                    }
                    prisonWardsOnScheme.add(new PrisonWard(x2Coridor + 1 + z * w4Size, w4Size - 2, aWall - y1Coridor + 1, y1Coridor - 3));
                    z++;
                }
        }
    }

    private void fillWards() {
        int sanitaryNook;
        for (PrisonWard ward : prisonWardsOnScheme) {
            prisonPlan[(int) ward.getStartX()][(int) ward.getStartY()] = Fields.WARD;
            prisonPlan[(int) ward.getStartX()][(int) ward.getEndY()] = Fields.WARD;
            prisonPlan[(int) ward.getEndX()][(int) ward.getStartY()] = Fields.WARD;
            prisonPlan[(int) ward.getEndX()][(int) ward.getEndY()] = Fields.WARD;
            sanitaryNook = rand.nextInt(4) + 1;
            switch (sanitaryNook) {
                case 1:
                    sanitaryNooksOnScheme.add(new SanitaryNook(ward.getStartX(), ward.getStartY()));
                    break;
                case 2:
                    sanitaryNooksOnScheme.add(new SanitaryNook(ward.getEndX() - SchemeGenerator.conditions.sizeOfSanitaryNook, ward.getStartY()));
                    break;
                case 3:
                    sanitaryNooksOnScheme.add(new SanitaryNook(ward.getStartX(), ward.getEndY() - SchemeGenerator.conditions.sizeOfSanitaryNook));
                    break;
                case 4:
                    sanitaryNooksOnScheme.add(new SanitaryNook(ward.getEndX() - SchemeGenerator.conditions.sizeOfSanitaryNook, ward.getEndY() - SchemeGenerator.conditions.sizeOfSanitaryNook));
                    break;
            }
        }
        for (SanitaryNook nook : sanitaryNooksOnScheme) {
            for (int w = (int) nook.getStartX(); w <= (int) nook.getEndX(); w++) {
                for (int h = (int) nook.getStartY(); h <= (int) nook.getEndY(); h++) {
                    prisonPlan[w][h] = Fields.SANITARY_NOOK;
                }
            }
        }
    }

    public void arrangePrisonScheme() {
        //TODO initializing PrisonScheme components and whole prison
    }

    public int getPlanSquareSize() {
        return planSquareSize;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Fields[][] getPrisonPlan() {
        return prisonPlan;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountOfPrisonWards() {
        return prisonWardsOnScheme.size();
    }

    public int getAmountOfSanitaryNooks() {
        return sanitaryNooksOnScheme.size();
    }

    public int getAmountOfDoors() {
        return doorsOnScheme.size();
    }

    public int getAmountOfLightBulbs() {
        return lightBulbsOnScheme.size();
    }

    public int getAmountOfCameras() {
        return camerasOnScheme.size();
    }

    public int getAmountOfWindows() {
        return windowsOnScheme.size();
    }
    
}
