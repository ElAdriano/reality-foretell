package Models;

import java.util.ArrayList;

public class PrisonScheme {
    private ArrayList<PrisonWard> prisonWards;
    private ArrayList<Door> doorsInScheme;
    private ArrayList<LightBulb> lightBulbsInScheme;
    private ArrayList<Camera> camerasInScheme;
    private ArrayList<Window> windowsInScheme;

    private MonitoringRoom monitoringRoom;
    private SanitaryNook sanitaryNook;

    private double rate;

    public PrisonScheme(MonitoringRoom monitoringRoom, SanitaryNook sanitaryNook){
        this.camerasInScheme = new ArrayList<>();
        this.doorsInScheme = new ArrayList<>();
        this.lightBulbsInScheme = new ArrayList<>();
        this.camerasInScheme = new ArrayList<>();
        this.windowsInScheme = new ArrayList<>();

        this.monitoringRoom = monitoringRoom;
        this.sanitaryNook = sanitaryNook;
    }

    public double getRate() {
        return rate;
    }
}
