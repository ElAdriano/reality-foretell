package Management;

import javafx.scene.image.Image;
import Models.PrisonScheme;
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
        ArrayList<PrisonScheme> generation = new ArrayList<>();
        this.rand = new Random();
        for (int i = 0; i < 2; i++) {
            PrisonScheme prisonScheme = new PrisonScheme();
            prisonScheme.arrangePrisonScheme();
            generation.add(prisonScheme);
            Image image = ImageCreator.createImage(prisonScheme.getPrisonPlan(), prisonScheme.getPlanSquareSize());
            ImageHolder.addImage(image, prisonScheme.getPrice());
        }
        PrisonScheme prisonScheme = makeCrossingOver(generation.get(0), generation.get(1));
        Image image = ImageCreator.createImage(prisonScheme.getPrisonPlan(), prisonScheme.getPlanSquareSize());
        ImageHolder.addImage(image, prisonScheme.getPrice());
        for (int i = 0; i < 97; i++) {
            prisonScheme = new PrisonScheme();
            prisonScheme.arrangePrisonScheme();
            generation.add(prisonScheme);
            image = ImageCreator.createImage(prisonScheme.getPrisonPlan(), prisonScheme.getPlanSquareSize());
            ImageHolder.addImage(image, prisonScheme.getPrice());
        }

        /*
        FINAL VERSION
        ArrayList<PrisonScheme> population = createFirstGeneration();
        PrisonScheme scheme = getBestIndividualInPopulation(population);
        ImageHolder.addImage(
                ImageCreator.createImage(scheme.getPrisonPlan(), scheme.getPlanSquareSize())
        );
        generateRestSchemesUsingGeneticAlgorithm(population);
        */
    }

    private void generateRestSchemesUsingGeneticAlgorithm(ArrayList<PrisonScheme> firstPopulation) {
        ArrayList<PrisonScheme> population = firstPopulation;

        for (int i = 1; i < 5; i++) {
            ArrayList<PrisonScheme> nextGeneration = createNextGeneration(population);
            population = nextGeneration;

            PrisonScheme bestIndividual = getBestIndividualInPopulation(nextGeneration);
            ImageHolder.addImage(
                    ImageCreator.createImage(bestIndividual.getPrisonPlan(), bestIndividual.getPlanSquareSize()),
                    bestIndividual.getPrice()
            );
        }
    }

    private ArrayList<PrisonScheme> createFirstGeneration() {
        ArrayList<PrisonScheme> generation = new ArrayList<>();
        // TODO creating first generation
        return generation;
    }

    // TODO
    @Override
    public double rateIndividual(PrisonScheme individual) {
        return 0;
    }

    @Override
    public PrisonScheme makeCrossingOver(PrisonScheme individual1, PrisonScheme individual2) {
        PrisonScheme babyPrison = new PrisonScheme();
        switch(rand.nextInt(2)+1) {
            case 1:
                babyPrison.setX1Corridor(individual1.getX1Corridor());
                babyPrison.setX2Corridor(individual1.getX2Corridor());
                break;
            case 2:
                babyPrison.setX1Corridor(individual2.getX1Corridor());
                babyPrison.setX2Corridor(individual2.getX2Corridor());
                break;

        }
        switch(rand.nextInt(2)+1) {
            case 1:
                babyPrison.setY1Corridor(individual1.getY1Corridor());
                babyPrison.setY2Corridor(individual1.getY2Corridor());
                break;
            case 2:
                babyPrison.setY1Corridor(individual2.getY1Corridor());
                babyPrison.setY2Corridor(individual2.getY2Corridor());
                break;

        }
        switch(rand.nextInt(2)+1) {
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
        switch(rand.nextInt(2)+1) {
            case 1:
                babyPrison.setW1(individual1.getW1());
                break;
            case 2:
                babyPrison.setW1(individual2.getW1());
                break;

        }
        switch(rand.nextInt(2)+1) {
            case 1:
                babyPrison.setW2(individual1.getW2());
                break;
            case 2:
                babyPrison.setW2(individual2.getW2());
                break;

        }
        switch(rand.nextInt(2)+1) {
            case 1:
                babyPrison.setW3(individual1.getW3());
                break;
            case 2:
                babyPrison.setW3(individual2.getW3());
                break;

        }
        switch(rand.nextInt(2)+1) {
            case 1:
                babyPrison.setW4(individual1.getW4());
                break;
            case 2:
                babyPrison.setW4(individual2.getW4());
                break;

        }
        babyPrison.arrangePrisonScheme();
        return babyPrison;
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
