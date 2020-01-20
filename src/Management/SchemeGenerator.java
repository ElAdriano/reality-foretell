package Management;

import Models.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class SchemeGenerator extends Thread implements GeneticAlgorithm {

    public static Conditions conditions = null;
    public Random rand;

    public static void initStaticConditions() {
        if (conditions == null) {
            conditions = new Conditions();
        }
    }

    @Override
    public void run() {
        ArrayList<PrisonScheme> population = createFirstGeneration();
        PrisonScheme scheme = getBestIndividualInPopulation(population);

        Image image = ImageCreator.createImage(scheme.getPrisonPlan(), scheme.getPlanSquareSize());
        ImageHolder.addImage(image, scheme.getPrice());

        generateRestSchemesUsingGeneticAlgorithm(population);
    }

    private ArrayList<PrisonScheme> createFirstGeneration() {
        ArrayList<PrisonScheme> population = new ArrayList<>();
        for (int i = 0; i < 105; i++) {
            PrisonScheme scheme = new PrisonScheme();
            scheme.arrangePrisonScheme();
            population.add(scheme);
        }
        return population;
    }

    private void generateRestSchemesUsingGeneticAlgorithm(ArrayList<PrisonScheme> firstPopulation) {
        ArrayList<PrisonScheme> population = firstPopulation;

        for (int i = 1; i < SchemeGenerator.conditions.amountOfGenerations; i++) {
            ArrayList<PrisonScheme> nextGeneration = createNextGeneration(population);
            population = nextGeneration;

            PrisonScheme bestIndividual = getBestIndividualInPopulation(nextGeneration);
            ImageHolder.addImage(
                    ImageCreator.createImage(bestIndividual.getPrisonPlan(), bestIndividual.getPlanSquareSize()),
                    bestIndividual.getPrice()
            );
        }
    }

    @Override
    public double rateIndividual(PrisonScheme individual) {
        if (individual == null) {
            return Double.NEGATIVE_INFINITY;
        }

        Fields[][] schemePlan = individual.getPrisonPlan();
        int size = individual.getPlanSquareSize();

        int sanitaryNooksSurface = 0;
        int monitoringRoomsSurface = 0;
        int prisonWardsSurface = 0;
        int corridorsSurface = 0;
        int bunksSurface = 0;
        int wholePrisonSurface = 0;
        for (int h = 0; h < size; h++) {
            for (int w = 0; w < size; w++) {
                Fields field = schemePlan[w][h];
                switch (field) {
                    case SANITARY_NOOK:
                        sanitaryNooksSurface++;
                        break;
                    case MONITORING_ROOM:
                        monitoringRoomsSurface++;
                        break;
                    case PRISON_WARD:
                        prisonWardsSurface++;
                        break;
                    case CORRIDOR:
                        corridorsSurface++;
                        break;
                    case BUNK:
                        bunksSurface++;
                        break;
                }
                if (field != Fields.OUTSIDE_FIELD) {
                    wholePrisonSurface++;
                }
            }
        }

        double aDoor = individual.getAmountOfDoors() * Door.getPrice() * Door.getPriority() / (individual.getAmountOfPrisonWards() + 2);
        double aWindow = individual.getAmountOfWindows() * Window.getPrice() * Window.getPriority() / (individual.getAmountOfPrisonWards() + 2);
        double bSanitaryNook = SanitaryNook.priority * sanitaryNooksSurface / wholePrisonSurface;
        double bMonitoringRoom = MonitoringRoom.priority * monitoringRoomsSurface / wholePrisonSurface;
        double cCamera = Camera.priority * SchemeGenerator.conditions.cameraRange * individual.getAmountOfCameras() * Camera.price / wholePrisonSurface;
        double dPrisonWard = PrisonWard.getPriority() * prisonWardsSurface * PrisonWard.getPrice() / wholePrisonSurface;
        double dCorridor = ((double) wholePrisonSurface - (double) corridorsSurface) / (double) wholePrisonSurface;
        double dBunks = bunksSurface * Bunk.getPriority() / wholePrisonSurface;

        double rate = Math.sqrt((Math.pow(aDoor, 2) + Math.pow(aWindow, 2)
                + Math.pow(bSanitaryNook, 2)
                + Math.pow(cCamera, 2) + Math.pow(dBunks, 3)
                + Math.pow(dPrisonWard, 2)) / 7 * dCorridor
        );
        return rate;
    }

    @Override
    public PrisonScheme makeCrossingOver(PrisonScheme individual1, PrisonScheme individual2) {
        PrisonScheme babyPrison = new PrisonScheme();
        rand = new Random();

        if (individual1 != null && individual2 == null) {
            return individual1;
        } else if (individual1 == null && individual2 != null) {
            return individual2;
        } else if (individual1 == null && individual2 == null) {
            return babyPrison;
        }

        switch (rand.nextInt(2) + 1) {
            case 1:
                babyPrison.setX1Corridor(individual1.getX1Corridor());
                babyPrison.setX2Corridor(individual1.getX2Corridor());
                break;
            case 2:
                babyPrison.setX1Corridor(individual2.getX1Corridor());
                babyPrison.setX2Corridor(individual2.getX2Corridor());
                break;
        }

        switch (rand.nextInt(2) + 1) {
            case 1:
                babyPrison.setY1Corridor(individual1.getY1Corridor());
                babyPrison.setY2Corridor(individual1.getY2Corridor());
                break;
            case 2:
                babyPrison.setY1Corridor(individual2.getY1Corridor());
                babyPrison.setY2Corridor(individual2.getY2Corridor());
                break;

        }
        switch (rand.nextInt(2) + 1) {
            case 1:
                babyPrison.setrMonitorRoomPlacement(individual1.getrMonitorRoomPlacement());
                babyPrison.setExtraCorridor(individual1.getExtraCorridor());
                babyPrison.setEntranceDoorPosition(individual1.getEntranceDoorPosition());
                break;
            case 2:
                babyPrison.setrMonitorRoomPlacement(individual2.getrMonitorRoomPlacement());
                babyPrison.setExtraCorridor(individual2.getExtraCorridor());
                babyPrison.setEntranceDoorPosition(individual2.getEntranceDoorPosition());
                break;

        }
        switch (rand.nextInt(2) + 1) {
            case 1:
                babyPrison.setW1(individual1.getW1());
                break;
            case 2:
                babyPrison.setW1(individual2.getW1());
                break;
        }
        switch (rand.nextInt(2) + 1) {
            case 1:
                babyPrison.setW2(individual1.getW2());
                break;
            case 2:
                babyPrison.setW2(individual2.getW2());
                break;

        }
        switch (rand.nextInt(2) + 1) {
            case 1:
                babyPrison.setW3(individual1.getW3());
                break;
            case 2:
                babyPrison.setW3(individual2.getW3());
                break;

        }
        switch (rand.nextInt(2) + 1) {
            case 1:
                babyPrison.setW4(individual1.getW4());
                break;
            case 2:
                babyPrison.setW4(individual2.getW4());
                break;

        }
        switch (rand.nextInt(2) + 1) {
            case 1:
                babyPrison.setBunksOnScheme(individual1.getBunksOnScheme());
                break;
            case 2:
                babyPrison.setBunksOnScheme(individual2.getBunksOnScheme());
                break;

        }
        switch (rand.nextInt(2) + 1) {
            case 1:
                babyPrison.setLightBulbsOnScheme(individual1.getLightBulbsOnScheme());
                break;
            case 2:
                babyPrison.setLightBulbsOnScheme(individual2.getLightBulbsOnScheme());
                break;

        }
        babyPrison.arrangePrisonScheme();
        return babyPrison;
    }

    @Override
    public ArrayList<PrisonScheme> createNextGeneration(ArrayList<PrisonScheme> population) {
        ArrayList<PrisonScheme> topIndividuals = filterTopIndividuals(population);

        ArrayList<PrisonScheme> nextGeneration = new ArrayList<>(topIndividuals);
        PrisonScheme createdIndividual;
        double rate;

        for (int i = 0; i < topIndividuals.size() - 1; i++) {
            for (int j = i + 1; j < topIndividuals.size(); j++) {
                createdIndividual = makeCrossingOver(topIndividuals.get(i), topIndividuals.get(j));

                rate = rateIndividual(createdIndividual);
                createdIndividual.setRate(rate);

                nextGeneration.add(createdIndividual);
            }
        }
        return nextGeneration;
    }

    @Override
    public PrisonScheme getBestIndividualInPopulation(ArrayList<PrisonScheme> population) {
        PrisonScheme returnedIndividual = population.get(0);
        for (int i = 1; i < population.size(); i++) {
            PrisonScheme individual = population.get(i);
            if (individual.getRate() > returnedIndividual.getRate()) {
                returnedIndividual = individual;
            }
        }
        return returnedIndividual;
    }

    @Override
    public ArrayList<PrisonScheme> filterTopIndividuals(ArrayList<PrisonScheme> population) {
        population.sort((a, b) -> {
            if (a.getRate() > b.getRate()) {
                return -1;
            } else {
                return 0;
            }
        });

        ArrayList<PrisonScheme> eliteIndividuals = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            eliteIndividuals.add(population.get(i));
        }
        return eliteIndividuals;
    }

}
