import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutoTradingSystemTest {
    AutoTradingSystem ats;
    @Mock
    MockDriver driver;

    @BeforeEach
    void setUp() {
        ats = new AutoTradingSystem();
        ats.selectStockerBrocker(driver);
    }

    @Test
    void createAutoTradingSystem() {
        assertNotNull(ats);
    }

    @Test
    void loginTest() {
        ats.login("id", "pass");

        verify(driver, times(1)).login("id", "pass");
    }

    @Test
    void buyTest() {
        ats.buy("code", 2, 3);

        verify(driver, times(1)).buy("code", 2, 3);
    }

    @Test
    void sellTest() {
        ats.sell("code", 2, 3);

        verify(driver, times(1)).sell("code", 2, 3);
    }

    @Test
    void getPriceTest() {
        ats.getPrice("code");

        verify(driver, times(1)).getPrice("code");
    }

    @Test
    void getPriceButStockerBrockerIsNotSelected() {
        AutoTradingSystem newAts = new AutoTradingSystem();
        try {
            newAts.getPrice("code");
            fail();
        }
        catch (BrockerNotSelectedException e) {
            assertNotNull(ats);
        }
    }
}