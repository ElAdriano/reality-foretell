package Management;

import Models.PrisonScheme;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class SchemeGenerator implements GeneticAlgorithm, Runnable {
    public static Conditions conditions = null;
    public ArrayList<Image> generatedImages;

    public static void initStaticConditions() {
        if (conditions == null) {
            conditions = new Conditions();
        }
    }

    @Override
    public void run() {
        generatedImages = new ArrayList<>();
        generateBestSchemeUsingGeneticAlgorithm();
    }

    public void generateBestSchemeUsingGeneticAlgorithm() {
        // TODO algorithm
        PrisonScheme prisonScheme = new PrisonScheme();
        Image bestIndividual = ImageCreator.createImage(prisonScheme.getPrisonPlan(), prisonScheme.getPlanSquareSize(), prisonScheme.getPlanSquareSize());
        generatedImages.add(bestIndividual);
    }

    // TODO
    @Override
    public double rateIndividual(PrisonScheme individual) {
        return 0;
    }

    // TODO
    @Override
    public ArrayList<PrisonScheme> filterTopIndividuals(ArrayList<PrisonScheme> population) {
        return null;
    }

    // TODO
    @Override
    public ArrayList<PrisonScheme> createNextGeneration(ArrayList<PrisonScheme> topIndividuals) {
        return null;
    }

    // TODO
    @Override
    public PrisonScheme getBestIndividualInPopulation(ArrayList<PrisonScheme> population) {
        return null;
    }

    // TODO
    @Override
    public PrisonScheme makeCrossingOver(PrisonScheme individual1, PrisonScheme individual2) {
        return null;
    }
}
