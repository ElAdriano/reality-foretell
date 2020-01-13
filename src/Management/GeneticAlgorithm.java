package Management;

interface GeneticAlgorithm {

    Population evolve(Population populationToEvolve);

    Individual crossover(Individual individual1, Individual individual2);

    void mutate(Individual individual);

    void rouletteSelection(Population population);

    void tournamentSelection(Population population);

}
