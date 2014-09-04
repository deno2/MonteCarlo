package com.immutable_ltd.simulation;

import java.math.BigDecimal;
import java.security.SecureRandom;

/**
 * Created by dene on 03/09/2014.
 */
public class BoxMullerRandom implements RandomNumberStrategy {
    private SecureRandom random;

    public BoxMullerRandom(){
        this.random = new SecureRandom();
    }

    public BoxMullerRandom(long seed){
        this.random = new SecureRandom();
        this.random.setSeed(seed);
    }

    @Override
    public BigDecimal generate() {
        double x, y, dist;

        do {
            x = (2 * this.random.nextDouble()) - 1;
            y = (2 * this.random.nextDouble()) - 1;
            dist = (x * x) + (y * y);
        } while (dist >= 1.0);

        return BigDecimal.valueOf(x * Math.sqrt((-2 * Math.log(dist)) / dist));
    }
}
