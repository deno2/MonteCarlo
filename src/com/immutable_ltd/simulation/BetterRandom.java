package com.immutable_ltd.simulation;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.stream.IntStream;

/**
 * Created by dene on 03/09/2014.
 */
public class BetterRandom implements RandomNumberStrategy {
    private SecureRandom random;
    private int n;

    public BetterRandom(int n){
       this.random = new SecureRandom();
        this.n = n;
    }

    public BetterRandom(int n, long seed){
        this.random = new SecureRandom();
        this.random.setSeed(seed);
        this.n = n;
    }

    @Override
    public BigDecimal generate() {
        double sum = IntStream.range(1, n + 1)
                        .mapToDouble(n -> random.nextDouble())
                        .sum() - (n / 2.0);

        return BigDecimal.valueOf(Math.sqrt(12.0 / n) * sum);
    }
}
