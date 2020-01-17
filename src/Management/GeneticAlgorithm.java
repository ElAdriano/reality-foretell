package Management;

import java.util.ArrayList;

interface GeneticAlgorithm {

    double rateIndividual(PrisonScheme individual);

    ArrayList<PrisonScheme> filterTopIndividuals(ArrayList<PrisonScheme> population);

    ArrayList<PrisonScheme> createNextGeneration(ArrayList<PrisonScheme> topIndividuals);

    PrisonScheme getBestIndividualInPopulation(ArrayList<PrisonScheme> population);

    PrisonScheme makeCrossingOver(PrisonScheme individual1, PrisonScheme individual2);

}
