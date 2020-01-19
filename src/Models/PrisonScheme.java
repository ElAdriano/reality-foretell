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

    private int x1Corridor;
    private int x2Corridor;
    private int y1Corridor;
    private int y2Corridor;

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

    private int entranceDoorPosition;

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

        int xMR = SchemeGenerator.conditions.xMinSizeOfMonitoringRoom;
        int yMR = SchemeGenerator.conditions.yMinSizeOfMonitoringRoom;
        x1Corridor = rand.nextInt((bWall - dWall)*2/3-xMR)+xMR;
        x2Corridor = (bWall-dWall- x1Corridor)/2 + x1Corridor;
        y1Corridor = rand.nextInt((aWall- cWall)*2/3-yMR)+yMR;
        y2Corridor = (aWall - cWall - y1Corridor)/2 + y1Corridor;

        rMonitorRoomPlacement = rand.nextInt(4)+1;

        switch (rMonitorRoomPlacement) {
            case 1:
                extraCorridor = 1;
                break;
            case 2:
            case 3:
                extraCorridor = rand.nextInt(2)*3+1;
                break;
            case 4:
                extraCorridor = 4;
                break;
        }
        int minSize = SchemeGenerator.conditions.minSizeOfWard;
        entranceDoorPosition = rand.nextInt(3) +1;
        w1 = rand.nextInt((aWall- y2Corridor) / minSize-1) + 2;
        w2 = rand.nextInt((cWall) / minSize-1) + 2;
        w3 = rand.nextInt((dWall) / minSize-1) + 2;
        w4 = rand.nextInt((bWall- x2Corridor) / minSize-1) + 2;
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
                if (w == 0 || h == aWall-1 ||
                        (h == 0 &&  w <= bWall- dWall) ||
                        (h <= cWall && w == bWall - dWall) ||
                        (h == cWall-1 && w >= bWall - dWall) ||
                        (h >= cWall && w == bWall-1)) {
                    prisonPlan[w][h] = Fields.WALL;
                }
            }
        }
    }

    private void addCorridors() {
        for (int w = 1; w < bWall-1; w++) {
            for (int h = 1; h < aWall-1; h++) {
                if ( (h < aWall - y1Corridor && w > x1Corridor && h > aWall - y2Corridor) || (w > x1Corridor && w < x2Corridor && h < aWall - y1Corridor)) {
                    prisonPlan[w][h] = Fields.CORRIDOR;
                }
            }
        }
    }

    private void addInsideWalls() {
        for (int w = 1; w < bWall-1; w++) {
            for (int h = 1; h < aWall-1; h++) {
                if ((w == x1Corridor && h < aWall - y1Corridor) ||
                        (w == x2Corridor && h <= aWall - y2Corridor) ||
                        (w >= x2Corridor && h == aWall - y2Corridor) ||
                        (w >= x1Corridor && h == aWall - y1Corridor) ||
                        (w <= x1Corridor && h == aWall - y1Corridor) ||
                        (w <= x1Corridor && h == aWall - y2Corridor) ||
                        (w == x1Corridor && h >= aWall - y1Corridor) ||
                        (w == x2Corridor && h >= aWall - y1Corridor) ||
                        (w >= x2Corridor && h == cWall-1) ||
                        (w == bWall - dWall && h <= aWall - y2Corridor) ){
                    prisonPlan[w][h] = Fields.WALL;
                }
            }
        }
    }

    private void addMonitoringRoom() {
        int dS = SchemeGenerator.conditions.doorSize;
        switch (rMonitorRoomPlacement) {
            case 1:
                for (int w = 1; w < bWall-1; w++) {
                    for (int h = 1; h < aWall-1; h++) {
                        if (  h < aWall - y1Corridor && w < x1Corridor && h > aWall - y2Corridor) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;

                        }
                    }
                }
                monitoringRoom = new MonitoringRoom(1, x1Corridor -2,aWall- y2Corridor +1, y2Corridor - y1Corridor -2);
                for (int i = 0; i < dS; i++) {
                    prisonPlan[x1Corridor][aWall-y2Corridor+(y2Corridor-y1Corridor-dS)/2+i] = Fields.DOOR;
                    prisonPlan[0][aWall-y2Corridor+(y2Corridor-y1Corridor-dS)/2+i] = Fields.WINDOW;
                }
                doorsOnScheme.add(new Door(x1Corridor, 1, aWall-y2Corridor+(y2Corridor-y1Corridor-dS)/2, dS));
                windowsOnScheme.add(new Window(0,1, aWall-y2Corridor+(y2Corridor-y1Corridor-dS)/2, dS));
                extraCorridor = 1;
                addExtraWards(2);
                addExtraWards(3);
                break;
            case 2:
                for (int w = 1; w < bWall-1; w++) {
                    for (int h = 1; h < aWall-1; h++) {
                        if (  h >= cWall && w < bWall-dWall && h < aWall - y2Corridor && w > x2Corridor) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;
                        }
                    }
                }
                monitoringRoom = new MonitoringRoom(x2Corridor +1,bWall-dWall- x2Corridor -2,cWall+1,aWall-cWall- y2Corridor -2);
                for (int i = 0; i < dS; i++) {
                    prisonPlan[x2Corridor][cWall+(aWall-cWall-y2Corridor-dS)/2+i] = Fields.DOOR;
                    prisonPlan[x2Corridor+(bWall-dWall-x2Corridor-dS)/2+i][aWall-y2Corridor] = Fields.DOOR;
                }
                doorsOnScheme.add(new Door(x2Corridor, 1, cWall+(aWall-cWall-y2Corridor-dS)/2, dS));
                doorsOnScheme.add(new Door(x2Corridor+(bWall-dWall-x2Corridor-dS)/2, dS, aWall-y2Corridor, 1));
                extraCorridor = rand.nextInt(2)*3+1;
                addExtraWards(3);
                addExtraWards(extraCorridor);
                break;
            case 3:
                for (int w = 1; w < bWall-1; w++) {
                    for (int h = 1; h < aWall-1; h++) {
                        if ( w < x1Corridor && h > aWall - y1Corridor) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;
                        }
                    }
                }
                monitoringRoom = new MonitoringRoom(1, x1Corridor -2,aWall- y1Corridor +1, y1Corridor -2);
                extraCorridor = rand.nextInt(2)*3 + 1;
                if (extraCorridor == 1) {
                    for (int i = 0; i < dS; i++) {
                        prisonPlan[0][aWall - y1Corridor + (y1Corridor - dS) / 2 + i] = Fields.WINDOW;
                        prisonPlan[x1Corridor][aWall - y1Corridor + (y1Corridor - dS) / 2 + i] = Fields.DOOR;
                    }
                    doorsOnScheme.add(new Door(x1Corridor, 1, aWall - y1Corridor + (y1Corridor - dS) / 2, dS));
                    windowsOnScheme.add(new Window(0,1, aWall - y1Corridor + (y1Corridor - dS) / 2, dS));
                } else {
                    for (int i = 0; i < dS; i++) {
                        prisonPlan[(x1Corridor - dS) / 2 + i][aWall - y1Corridor] = Fields.DOOR;
                        prisonPlan[(x1Corridor - dS) / 2 + i][aWall - 1] = Fields.WINDOW;
                    }
                    doorsOnScheme.add(new Door((x1Corridor - dS) / 2, dS, aWall - y1Corridor, 1));
                    windowsOnScheme.add(new Window((x1Corridor - dS) / 2,dS, aWall - 1, 1));
                }
                addExtraWards(2);
                addExtraWards(extraCorridor);
                break;
            case 4:
                for (int w = 1; w < bWall-1; w++) {
                    for (int h = 1; h < aWall-1; h++) {
                        if ( w > x1Corridor && h > aWall - y1Corridor && w < x2Corridor) {
                            prisonPlan[w][h] = Fields.MONITORING_ROOM;
                        }
                    }
                }
                monitoringRoom = new MonitoringRoom(x1Corridor +1, x2Corridor - x1Corridor -2,aWall- y1Corridor +1, y1Corridor -2);
                extraCorridor = 4;
                for (int i = 0; i < dS; i++) {
                    prisonPlan[x1Corridor+(x2Corridor-x1Corridor-dS)/2+i][aWall-y1Corridor] = Fields.DOOR;
                    prisonPlan[x1Corridor+(x2Corridor-x1Corridor-dS)/2+i][aWall-1] = Fields.WINDOW;
                }
                doorsOnScheme.add(new Door(x1Corridor+(x2Corridor-x1Corridor-dS)/2, dS, aWall - y1Corridor, 1));
                windowsOnScheme.add(new Window(x1Corridor+(x2Corridor-x1Corridor-dS)/2,dS, aWall-1, 1));
                addExtraWards(2);
                addExtraWards(3);
                break;
        }

        addExtraCorridor(extraCorridor);
    }

    private void addExtraCorridor(int a) {
        switch (a) {
            case 1:
                for (int w = 1; w < bWall-1; w++) {
                    for (int h = 1; h < aWall-1; h++) {
                        if ( w > x1Corridor && h >= aWall - y1Corridor && w < x2Corridor) {
                            prisonPlan[w][h] = Fields.CORRIDOR;
                        }
                    }
                }
                break;
            case 4:
                for (int w = 1; w < bWall-1; w++) {
                    for (int h = 1; h < aWall-1; h++) {
                        if (  h < aWall - y1Corridor && w <= x1Corridor && h > aWall - y2Corridor) {
                            prisonPlan[w][h] = Fields.CORRIDOR;
                        }
                    }
                }
                break;
        }
    }

    private void addExtraWards(int a) {
        int dS = SchemeGenerator.conditions.doorSize;
        switch (a) {
            case 1:
                prisonWardsOnScheme.add(new PrisonWard(1, x1Corridor -2,aWall- y2Corridor +1, y2Corridor - y1Corridor -2));
                for (int i = 0; i < dS; i++) {
                    prisonPlan[x1Corridor][aWall-y2Corridor+(y2Corridor-y1Corridor-dS)/2+i] = Fields.DOOR;
                    prisonPlan[0][aWall-y2Corridor+(y2Corridor-y1Corridor-dS)/2+i] = Fields.WINDOW;
                }
                doorsOnScheme.add(new Door(x1Corridor, 1, aWall-y2Corridor+(y2Corridor-y1Corridor-dS)/2, dS));
                windowsOnScheme.add(new Window(0,1, aWall-y2Corridor+(y2Corridor-y1Corridor-dS)/2, dS));
                break;
            case 2:
                for (int w = 1; w < bWall-1; w++) {
                    for (int h = 1; h < aWall-1; h++) {
                        if (  h >= cWall && w < bWall-dWall && h < aWall - y2Corridor && w > x2Corridor) {
                            prisonPlan[w][h] = Fields.TECHNICAL_ROOM;
                        }
                    }
                }
                for (int i = 0; i < dS; i++) {
                    prisonPlan[x2Corridor][cWall+(aWall-cWall-y2Corridor-dS)/2+1] = Fields.DOOR;
                    prisonPlan[x2Corridor+(bWall-dWall-x2Corridor-dS)/2+i][aWall-y2Corridor] = Fields.DOOR;
                }
                doorsOnScheme.add(new Door(x2Corridor, 1, cWall+(aWall-cWall-y2Corridor-dS)/2, dS));
                doorsOnScheme.add(new Door(x2Corridor+(bWall-dWall-x2Corridor-dS)/2, dS, aWall-y2Corridor, 1));
                break;
            case 3:
                prisonWardsOnScheme.add(new PrisonWard(1, x1Corridor -2,aWall- y1Corridor +1, y1Corridor -3));
                if (extraCorridor == 1) {
                    for (int i = 0; i < dS; i++) {
                        prisonPlan[x1Corridor][aWall - y1Corridor + (y1Corridor - dS) / 2 + i] = Fields.DOOR;
                        prisonPlan[0][aWall - y1Corridor + (y1Corridor - dS) / 2 + i] = Fields.WINDOW;
                    }
                    doorsOnScheme.add(new Door(x1Corridor, 1, aWall - y1Corridor + (y1Corridor - dS) / 2, dS));
                    windowsOnScheme.add(new Window(0,1, aWall - y1Corridor + (y1Corridor - dS) / 2, dS));
                } else {
                    for (int i = 0; i < dS; i++) {
                        prisonPlan[(x1Corridor - dS) / 2 + i][aWall - y1Corridor] = Fields.DOOR;
                        prisonPlan[(x1Corridor - dS) / 2 + i][aWall - 1] = Fields.WINDOW;
                    }
                    doorsOnScheme.add(new Door((x1Corridor - dS) / 2, dS, aWall - y1Corridor, 1));
                    windowsOnScheme.add(new Window((x1Corridor - dS) / 2,dS, aWall - 1, 1));
                }
                break;
            case 4:
                prisonWardsOnScheme.add(new PrisonWard(x1Corridor +1, x2Corridor - x1Corridor -2,aWall- y1Corridor +1, y1Corridor -3));
                for (int i = 0; i < dS; i++) {
                    prisonPlan[x1Corridor+(x2Corridor-x1Corridor-dS)/2+i][aWall-y1Corridor] = Fields.DOOR;
                    prisonPlan[x1Corridor+(x2Corridor-x1Corridor-dS)/2+i][aWall-1] = Fields.WINDOW;
                }
                doorsOnScheme.add(new Door(x1Corridor+(x2Corridor-x1Corridor-dS)/2, dS, aWall - y1Corridor, 1));
                windowsOnScheme.add(new Window(x1Corridor+(x2Corridor-x1Corridor-dS)/2,dS, aWall-1, 1));
                break;
        }
    }

    private void addEntranceDoor() {

        int dS = SchemeGenerator.conditions.doorSize;
        switch (entranceDoorPosition) {
            case 1:
                for (int i = 0; i < dS; i++) {
                    prisonPlan[(x2Corridor + x1Corridor -dS)/2+i][0] = Fields.DOOR;
                }
                break;
            case 2:
                for (int i = 0; i < dS; i++) {
                    prisonPlan[bWall-1][aWall - (y1Corridor + y2Corridor +dS)/2+i] = Fields.DOOR;
                }
                break;
            case 3:
                switch (extraCorridor) {
                    case 2:
                        for (int i = 0; i < dS; i++) {
                            prisonPlan[0][aWall - (y1Corridor + y2Corridor +dS)/2+i] = Fields.DOOR;
                        }
                        break;
                    case 1:
                        for (int i = 0; i < dS; i++) {
                            prisonPlan[(x2Corridor + x1Corridor -dS)/2+i][aWall-1] = Fields.DOOR;
                        }
                        break;
                }
                break;
        }
    }

    private void addWards() {
        int dS = SchemeGenerator.conditions.doorSize;
        int z;

        w1Size = (aWall- y2Corridor -1)/w1;
        z = 1;
        prisonWardsOnScheme.add(new PrisonWard(1, x1Corridor -2, 1, w1Size-2));
        for (int i = 0; i < dS; i++) {
            prisonPlan[x1Corridor][(w1Size-dS)/2+i] = Fields.DOOR;
            prisonPlan[0][i+(w1Size-dS)/2] = Fields.WINDOW;
        }
        doorsOnScheme.add(new Door(x1Corridor, 1, (w1Size-dS)/2, dS));
        windowsOnScheme.add(new Window(0,1, (w1Size-dS)/2, dS));
        while (z<w1) {
            for (int w = 1; w < x1Corridor; w++) {
                for (int h = 1; h < aWall - y2Corridor; h++) {
                    if ( h ==  z*w1Size ) {
                        prisonPlan[w][h] = Fields.WALL;
                    }
                }
            }
            prisonWardsOnScheme.add(new PrisonWard(1, x1Corridor -2, 1+z*(w1Size), w1Size-2));
            for (int i = 0; i < dS; i++) {
                prisonPlan[x1Corridor][w1Size*z+i+(w1Size-dS)/2] = Fields.DOOR;
                prisonPlan[0][w1Size*z+i+(w1Size-dS)/2] = Fields.WINDOW;
            }
            doorsOnScheme.add(new Door(x1Corridor, 1, w1Size*z+(w1Size-dS)/2, dS));
            windowsOnScheme.add(new Window(0,1, w1Size*z+(w1Size-dS)/2, dS));
            z++;
        }

        w2Size = (cWall-1)/w2;
        z = 1;
        prisonWardsOnScheme.add(new PrisonWard(x2Corridor +1, bWall-dWall- x2Corridor -2, 1, w2Size-2));
        for (int i = 0; i < dS; i++) {
            prisonPlan[x2Corridor][(w2Size-dS)/2+i] = Fields.DOOR;
            prisonPlan[bWall-dWall][i+(w2Size-dS)/2] = Fields.WINDOW;
        }
        doorsOnScheme.add(new Door(x2Corridor, 1, (w2Size-dS)/2, dS));
        windowsOnScheme.add(new Window(bWall-dWall,1, (w2Size-dS)/2, dS));
        while (z<w2) {
            for (int w = x2Corridor +1; w < bWall-dWall; w++) {
                for (int h = 1; h < cWall; h++) {
                    if ( h ==  z*w2Size ) {
                        prisonPlan[w][h] = Fields.WALL;
                    }
                }
            }
            prisonWardsOnScheme.add(new PrisonWard(x2Corridor +1, bWall-dWall- x2Corridor -2, 1+z*(w2Size), w2Size-2));
            for (int i = 0; i < dS; i++) {
                prisonPlan[x2Corridor][w2Size*z+i+(w2Size-dS)/2] = Fields.DOOR;
                prisonPlan[bWall-dWall][w2Size*z+i+(w2Size-dS)/2] = Fields.WINDOW;
            }
            doorsOnScheme.add(new Door(x2Corridor, 1, w2Size*z+(w2Size-dS)/2, dS));
            windowsOnScheme.add(new Window(bWall-dWall,1, aWall-y2Corridor+(y2Corridor-y1Corridor-dS)/2, dS));
            z++;
        }

        w3Size = (dWall-1)/w3;
        z =1;
        prisonWardsOnScheme.add(new PrisonWard(bWall-dWall+1, w3Size-2, cWall, aWall-cWall- y2Corridor -1));
        for (int i = 0; i < dS; i++) {
            prisonPlan[bWall-dWall+(w3Size-dS)/2+i][aWall-y2Corridor] = Fields.DOOR;
            prisonPlan[bWall-dWall+(w3Size-dS)/2+i][cWall-1] = Fields.WINDOW;
        }
        doorsOnScheme.add(new Door(bWall-dWall+(w3Size-dS)/2, dS, aWall-y2Corridor, 1));
        windowsOnScheme.add(new Window(bWall-dWall+(w3Size-dS)/2,dS, cWall-1, 1));
        while (z<w3) {
            for (int w = bWall-dWall; w < bWall; w++) {
                for (int h = cWall + 1; h < aWall - y2Corridor; h++) {
                    if ( w ==  bWall - dWall + z*w3Size ) {
                        prisonPlan[w][h] = Fields.WALL;
                    }
                }
            }
            prisonWardsOnScheme.add(new PrisonWard(bWall-dWall+1+z*w3Size, w3Size-2, cWall, aWall-cWall- y2Corridor -1));
            for (int i = 0; i < dS; i++) {
                prisonPlan[w3Size*z+bWall-dWall+(w3Size-dS)/2+i][aWall-y2Corridor] = Fields.DOOR;
                prisonPlan[w3Size*z+bWall-dWall+(w3Size-dS)/2+i][cWall-1] = Fields.WINDOW;
            }
            doorsOnScheme.add(new Door(w3Size*z+bWall-dWall+(w3Size-dS)/2, dS, aWall-y2Corridor, 1));
            windowsOnScheme.add(new Window(w3Size*z+bWall-dWall+(w3Size-dS)/2,dS, cWall-1, 1));
            z++;
        }

        w4Size = (bWall- x2Corridor -1)/w4;
        z = 1;
        prisonWardsOnScheme.add(new PrisonWard(x2Corridor +1, w4Size-2, aWall- y1Corridor +1, y1Corridor -3));
        for (int i = 0; i < dS; i++) {
            prisonPlan[x2Corridor+(w4Size-dS)/2+i][aWall-y1Corridor] = Fields.DOOR;
            prisonPlan[x2Corridor+(w4Size-dS)/2+i][aWall-1] = Fields.WINDOW;
        }
        doorsOnScheme.add(new Door(x2Corridor+(w4Size-dS)/2, dS, aWall-y1Corridor, 1));
        windowsOnScheme.add(new Window(x2Corridor+(w4Size-dS)/2,dS, aWall-1, 1));
        while (z < w4) {
            for (int w = x2Corridor +1; w < bWall; w++) {
                for (int h = aWall- y1Corridor +1; h < aWall; h++) {
                    if ( w ==  x2Corridor + z*w4Size ) {
                        prisonPlan[w][h] = Fields.WALL;
                    }
                }
            }
            prisonWardsOnScheme.add(new PrisonWard(x2Corridor +1+z*w4Size, w4Size-2, aWall- y1Corridor +1, y1Corridor -3));
            for (int i = 0; i < dS; i++) {
                prisonPlan[w4Size*z+x2Corridor+(w4Size-dS)/2+i][aWall-y1Corridor] = Fields.DOOR;
                prisonPlan[w4Size*z+x2Corridor+(w4Size-dS)/2+i][aWall-1] = Fields.WINDOW;
            }
            doorsOnScheme.add(new Door(w4Size*z+x2Corridor+(w4Size-dS)/2, dS, aWall-y1Corridor, 1));
            windowsOnScheme.add(new Window(w4Size*z+x2Corridor+(w4Size-dS)/2,dS, aWall-1, 1));
            z++;
        }
    }

    private void fillWards() {
        int sanitaryNook;
        for (PrisonWard ward: prisonWardsOnScheme){
             ward.setSanitaryNookPosition(rand.nextInt(4)+1);
             switch (ward.getSanitaryNookPosition()) {
                 case 1:
                     sanitaryNooksOnScheme.add(new SanitaryNook(ward.getStartX(), ward.getStartY()));
                     break;
                 case 2:
                     sanitaryNooksOnScheme.add(new SanitaryNook(ward.getEndX()-SchemeGenerator.conditions.sizeOfSanitaryNook, ward.getStartY()));
                     break;
                 case 3:
                     sanitaryNooksOnScheme.add(new SanitaryNook(ward.getStartX(), ward.getEndY()-SchemeGenerator.conditions.sizeOfSanitaryNook));
                     break;
                 case 4:
                     sanitaryNooksOnScheme.add(new SanitaryNook(ward.getEndX()-SchemeGenerator.conditions.sizeOfSanitaryNook, ward.getEndY()-SchemeGenerator.conditions.sizeOfSanitaryNook));
                     break;
             }

        }
        for (SanitaryNook nook: sanitaryNooksOnScheme) {
            for (int w = (int)nook.getStartX(); w <= (int)nook.getEndX(); w++) {
                for (int h = (int)nook.getStartY(); h <= (int)nook.getEndY(); h++) {
                        prisonPlan[w][h] = Fields.SANITARY_NOOK;
                }
            }
        }
    }

    public void arrangePrisonScheme() {
        addOutsideWalls();
        addCorridors();
        addInsideWalls();
        addMonitoringRoom();
        addEntranceDoor();
        addWards();
        fillWards();
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

    public int getX1Corridor() {
        return x1Corridor;
    }

    public void setX1Corridor(int x1Corridor) {
        this.x1Corridor = x1Corridor;
    }

    public int getX2Corridor() {
        return x2Corridor;
    }

    public void setX2Corridor(int x2Corridor) {
        this.x2Corridor = x2Corridor;
    }

    public int getY1Corridor() {
        return y1Corridor;
    }

    public void setY1Corridor(int y1Corridor) {
        this.y1Corridor = y1Corridor;
    }

    public int getY2Corridor() {
        return y2Corridor;
    }

    public void setY2Corridor(int y2Corridor) {
        this.y2Corridor = y2Corridor;
    }

    public int getW1() {
        return w1;
    }

    public void setW1(int w1) {
        this.w1 = w1;
    }

    public int getW2() {
        return w2;
    }

    public void setW2(int w2) {
        this.w2 = w2;
    }

    public int getW3() {
        return w3;
    }

    public void setW3(int w3) {
        this.w3 = w3;
    }

    public int getW4() {
        return w4;
    }

    public void setW4(int w4) {
        this.w4 = w4;
    }

    public int getrMonitorRoomPlacement() {
        return rMonitorRoomPlacement;
    }

    public void setrMonitorRoomPlacement(int rMonitorRoomPlacement) {
        this.rMonitorRoomPlacement = rMonitorRoomPlacement;
    }

    public int getExtraCorridor() {
        return extraCorridor;
    }

    public void setExtraCorridor(int extraCorridor) {
        this.extraCorridor = extraCorridor;
    }

    public int getEntranceDoorPosition() {
        return entranceDoorPosition;
    }

    public void setEntranceDoorPosition(int entranceDoorPosition) {
        this.entranceDoorPosition = entranceDoorPosition;
    }
}
