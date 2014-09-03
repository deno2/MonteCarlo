import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.stream.IntStream;

/**
 * Created by dene on 03/09/2014.
 */
public class FastRandom implements RandomNumberStrategy {
    private SecureRandom random;

    public FastRandom(){
        random = new SecureRandom();
    }

    public FastRandom(long seed){
        random = new SecureRandom();
        random.setSeed(seed);
    }

    @Override
    public BigDecimal generate() {
        return BigDecimal.valueOf(
                IntStream.range(1, 13)
                .mapToDouble(n -> random.nextDouble())
                .sum() - 6);
    }
}
