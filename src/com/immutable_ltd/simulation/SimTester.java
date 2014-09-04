package com.immutable_ltd.simulation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.logging.XMLFormatter;

/**
 * Created by dene on 03/09/2014.
 */
public class SimTester {
    private static void pf(String format, Object... args){
        System.out.printf(format, args);
    }

    /**
     * Exercises each of the Normal random number generators.
     * @param amount
     *        The number of each type of Normal random number to generate.
     */
    public static void randomNumbers(int amount){
        long seed = 10;
        ArrayList<BigDecimal> quicks = new ArrayList<>();
        ArrayList<BigDecimal> betters = new ArrayList<>();
        ArrayList<BigDecimal> boxMullers = new ArrayList<>();

        RandomNumberStrategy quick = new FastRandom(seed);
        RandomNumberStrategy better = new BetterRandom(56, seed);
        RandomNumberStrategy boxMuller = new BoxMullerRandom(seed);

        // generate the numbers
        int count = 0;
        while (count < amount){
            quicks.add(quick.generate());
            betters.add(better.generate());
            boxMullers.add(boxMuller.generate());
            count++;
        }

        // display results
        DoubleSummaryStatistics quickStats = quicks.stream().mapToDouble(x -> x.doubleValue()).summaryStatistics();
        DoubleSummaryStatistics bettersStats = betters.stream().mapToDouble(x -> x.doubleValue()).summaryStatistics();
        DoubleSummaryStatistics boxMullerStats = boxMullers.stream().mapToDouble(x -> x.doubleValue()).summaryStatistics();

        pf("FastRandom%n----------------%nMax: %7.3f, Min: %7.3f, Mean: %7.3f, Sum: %8.3f",
                quickStats.getMax(),
                quickStats.getMin(),
                quickStats.getAverage(),
                quickStats.getSum());

        pf("%s", ", Sample: ");
        quicks.stream().limit(10).forEach(x -> pf("%7.4f ", x));

        pf("%n%nBetterRandom%n----------------%nMax: %7.3f, Min: %7.3f, Mean: %7.3f, Sum: %8.3f",
                bettersStats.getMax(),
                bettersStats.getMin(),
                bettersStats.getAverage(),
                bettersStats.getSum());

        pf("%s", ", Sample: ");
        betters.stream().limit(10).forEach(x -> pf("%7.4f ", x));

        pf("%n%nBoxMullerRandom%n----------------%nMax: %7.3f, Min: %7.3f, Mean: %7.3f, Sum: %8.3f",
                boxMullerStats.getMax(),
                boxMullerStats.getMin(),
                boxMullerStats.getAverage(),
                boxMullerStats.getSum());

        pf("%s", ", Sample: ");
        boxMullers.stream().limit(10).forEach(x -> pf("%7.4f ", x));

    }

    public static void main(String[] args){
        pf("%s%n----------------%n%n", "Simulation Tests");
        randomNumbers(500);
    }
}
