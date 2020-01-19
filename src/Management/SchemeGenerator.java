package Management;

import Models.*;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class SchemeGenerator extends Thread implements GeneticAlgorithm {
    
    public static Conditions conditions = null;

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
            population.add(new PrisonScheme());
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
        Fields[][] schemePlan = individual.getPrisonPlan();
        int size = individual.getPlanSquareSize();

        int sanitaryNooksSurface = 0;
        int monitoringRoomsSurface = 0;
        int prisonWardsSurface = 0;
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
                }
                if (field != Fields.OUTSIDE_FIELD) {
                    wholePrisonSurface++;
                }
            }
        }

        double aDoor = individual.getAmountOfDoors() * Door.price * Door.priority / (individual.getAmountOfPrisonWards() + 2);
        double aWindow = individual.getAmountOfWindows() * Window.price * Window.priority / (individual.getAmountOfPrisonWards() + 2);
        double bSanitaryNook = SanitaryNook.priority * sanitaryNooksSurface / wholePrisonSurface;
        double bMonitoringRoom = MonitoringRoom.priority * monitoringRoomsSurface / wholePrisonSurface;
        double cCamera = Camera.priority * SchemeGenerator.conditions.cameraRange * individual.getAmountOfCameras() * Camera.price / wholePrisonSurface;
        double dPrisonWard = PrisonWard.priority * prisonWardsSurface * PrisonWard.price / wholePrisonSurface;

        double rate = Math.sqrt((Math.pow(aDoor, 2) + Math.pow(aWindow, 2)
                + Math.pow(bSanitaryNook, 2) + Math.pow(bMonitoringRoom, 2)
                + Math.pow(cCamera, 2)
                + Math.pow(dPrisonWard, 2)) / 7
        );
        return rate;
    }

    // TODO
    @Override
    public PrisonScheme makeCrossingOver(PrisonScheme individual1, PrisonScheme individual2) {
        return null;
    }

    // -------------------------------------------------------------------------

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
                return 1;
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
