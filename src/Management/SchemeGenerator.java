package Management;

import Models.PrisonScheme;
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
        for (int i = 0; i < 5; i++) {
            PrisonScheme prisonScheme = new PrisonScheme();
            Image image = ImageCreator.createImage(prisonScheme.getPrisonPlan(), prisonScheme.getPlanSquareSize());
            ImageHolder.addImage(image);
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
                    ImageCreator.createImage(bestIndividual.getPrisonPlan(), bestIndividual.getPlanSquareSize())
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
