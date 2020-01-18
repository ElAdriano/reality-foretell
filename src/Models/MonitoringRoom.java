package Models;

import Management.SchemeGenerator;

public class MonitoringRoom extends ComplexModel{

    public MonitoringRoom(double startH, double startW ){
        super(startH, SchemeGenerator.conditions.xMinSizeOfMonitoringRoom, startW, SchemeGenerator.conditions.yMinSizeOfMonitoringRoom, 10000, 0.5);
    }
}
