package pl.wilanowskiartur;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wilanowskiartur.requirements.ApiSearchRequirements;
import pl.wilanowskiartur.requirements.RandomSearchRequirements;
import pl.wilanowskiartur.services.NumberService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecruitmentTaskTests {

    private static final int API_MIN_VALUE = -1000;
    private static final int API_MAX_VALUE = 1000;
    private static final int RANDOM_MIN_VALUE = -1000;
    private static final int RANDOM_MAX_VALUE = 1000;
    private static final int ZERO = 0;
    private static final String EXCEPTION_MESSAGE = "The maximum value must be greater than the minimum value";

    @Autowired
    private NumberService numberService;

    @Test
    void contextLoads() {
    }

    @Test
    void runWithoutException() throws Exception {
        ApiSearchRequirements apiSearchRequirements = ApiSearchRequirements.builder().min(API_MIN_VALUE).max(API_MAX_VALUE).build();
        RandomSearchRequirements randomSearchRequirements= RandomSearchRequirements.builder().min(RANDOM_MIN_VALUE).max(RANDOM_MAX_VALUE).build();
        Integer sum = numberService.sum(apiSearchRequirements, randomSearchRequirements);
        assertNotNull(sum);
    }

    @Test()
    void whenExceptionThrown_thenAssertionSucceeds() throws NumberFormatException {
        ApiSearchRequirements apiSearchRequirements = ApiSearchRequirements.builder().min(ZERO).max(ZERO).build();
        RandomSearchRequirements randomSearchRequirements = RandomSearchRequirements.builder().min(ZERO).max(ZERO).build();
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            numberService.sum(apiSearchRequirements, randomSearchRequirements);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(EXCEPTION_MESSAGE));
    }
}
