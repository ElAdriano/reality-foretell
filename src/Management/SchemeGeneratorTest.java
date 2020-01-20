package Management;

import Models.PrisonScheme;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class SchemeGeneratorTest {

    @Test
    void rateIndividual(PrisonScheme individual) {
        SchemeGenerator generator = new SchemeGenerator();
        Double rate;

        rate = generator.rateIndividual(individual);
        assertTrue(rate instanceof Double);

        rate = generator.rateIndividual(null);
        assertTrue(rate == Double.NEGATIVE_INFINITY);
    }

    @Test
    void makeCrossingOver(PrisonScheme ind1, PrisonScheme ind2) {
        PrisonScheme crossingResult;
        SchemeGenerator generator = new SchemeGenerator();

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
    void createNextGeneration() {
        SchemeGenerator generator = new SchemeGenerator();
        ArrayList<PrisonScheme> firstPopulation = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            firstPopulation.add(new PrisonScheme());
        }

        ArrayList<PrisonScheme> population = new ArrayList<>();
        population = generator.createNextGeneration(firstPopulation);
        for (PrisonScheme prison : population) {
            if (!(prison instanceof PrisonScheme)) {
                assertEquals(true, false);
            }
        }
        assertEquals(true, true);
    }
}