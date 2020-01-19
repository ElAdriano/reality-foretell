package Models;

import Management.SchemeGenerator;

public class MonitoringRoom extends ComplexModel{

    public MonitoringRoom(double startX, double sizeX, double startY, double sizeY ){
        super(startX, sizeX, startY, sizeY, 10000, 0.5);
    }
}
