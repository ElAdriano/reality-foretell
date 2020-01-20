package Management;

import Models.PrisonScheme;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SchemeGeneratorTest {

    private SchemeGenerator generator;

    public SchemeGeneratorTest() {
        SchemeGenerator.initStaticConditions();
        SchemeGenerator.conditions.aDimensionOfPrison = 120;
        SchemeGenerator.conditions.bDimensionOfPrison = 120;
        SchemeGenerator.conditions.cDimensionOfPrison = 60;
        SchemeGenerator.conditions.dDimensionOfPrison = 60;
        generator = new SchemeGenerator();
    }

    @Test
    public void rateIndividual() {
        Double rate;
        PrisonScheme individual = new PrisonScheme();

        rate = generator.rateIndividual(individual);
        assertTrue(rate instanceof Double);

        rate = generator.rateIndividual(null);
        assertTrue(rate == Double.NEGATIVE_INFINITY);
    }

    @Test
    public void makeCrossingOver() {
        PrisonScheme ind1 = new PrisonScheme();
        PrisonScheme ind2 = new PrisonScheme();

        PrisonScheme crossingResult;

        crossingResult = generator.makeCrossingOver(ind1, null);
        assertTrue(crossingResult.equals(ind1));

        crossingResult = generator.makeCrossingOver(null, ind2);
        assertTrue(crossingResult.equals(ind2));

        crossingResult = generator.makeCrossingOver(null, null);
        assertTrue(crossingResult instanceof PrisonScheme);

        crossingResult = generator.makeCrossingOver(ind1, ind2);
        assertTrue(crossingResult instanceof PrisonScheme);
    }

    @Test
    public void createNextGeneration() {
        ArrayList<PrisonScheme> firstPopulation = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            firstPopulation.add(new PrisonScheme());
        }

        ArrayList<PrisonScheme> population;
        population = generator.createNextGeneration(firstPopulation);
        for (PrisonScheme prison : population) {
            if (!(prison instanceof PrisonScheme)) {
                fail();
            }
        }
        assertTrue(true);
    }

    @Test
    public void getBestIndividualInPopulation() {

        ArrayList<PrisonScheme> population = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            population.add(new PrisonScheme());
        }

        PrisonScheme bestIndividualToCheck = generator.getBestIndividualInPopulation(population);

        PrisonScheme bestIndividual = population.get(0);
        PrisonScheme iterativeIndividual;
        for (int i = 1; i < 100; i++) {
            iterativeIndividual = population.get(i);
            if (bestIndividual.getRate() < iterativeIndividual.getRate()) {
                bestIndividual = iterativeIndividual;
            }
        }

        assertEquals(bestIndividual, bestIndividualToCheck);
    }

    @Test
    public void filterTopIndividuals() {
        ArrayList<PrisonScheme> population = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            population.add(new PrisonScheme());
        }

        ArrayList<PrisonScheme> bestIndividuals = generator.filterTopIndividuals(population);

        population.sort((a, b) -> {
            if (a.getRate() > b.getRate()) {
                return -1;
            } else {
                return 0;
            }
        });

        for (int i = 0; i < bestIndividuals.size(); i++) {
            if (!bestIndividuals.get(i).equals(population.get(i))) {
                fail();
            }
        }
        assertTrue(true);
    }

}