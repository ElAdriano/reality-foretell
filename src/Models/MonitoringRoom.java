package Models;

import Management.Conditions;
import Management.SchemeGenerator;

public class MonitoringRoom extends ComplexModel{

    public MonitoringRoom(double startX, double startY, Door door, Camera camera, LightBulb lightBulb, Window window){
        super(startX, SchemeGenerator.conditions.xSizeOfMonitoringRoom, startY, SchemeGenerator.conditions.ySizeOfMonitoringRoom, door, camera, lightBulb, window, 10000, 0.5);
    }
}
