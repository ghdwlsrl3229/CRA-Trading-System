import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
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
        ats.login("ANY", "ANY");
        verify(driver, times(1)).login("ANY", "ANY");
    }
    @Test
    void loginTest_null_pass() {
        ats.login("ANY", null);
        verify(driver, times(0)).login("ANY", null);
    }
    @Test
    void loginTest_null_id() {
        ats.login(null, "ANY");
        verify(driver, times(0)).login(null, "ANY");
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

    @Test
    void buyNiceTimingSuccess() {
        int[] prices = {1, 2, 3, 4};
        int balance = 13;
        when(driver.getPrice("code"))
                .thenReturn(prices[0])
                .thenReturn(prices[1])
                .thenReturn(prices[2]);

        ats.buyNiceTiming("code", balance);
        verify(driver, times(3)).getPrice("code");
        verify(driver, times(1)).buy("code", balance/prices[2], prices[2]);
    }

    @Test
    void buyNiceTimingNotExecuted() {
        int[] prices = {1, 3, 2};
        int balance = 6;
        when(driver.getPrice("code"))
                .thenReturn(prices[0])
                .thenReturn(prices[1])
                .thenReturn(prices[2]);

        ats.buyNiceTiming("code", balance);
        verify(driver, times(3)).getPrice("code");
        verify(driver, never()).buy("code", balance/prices[2], prices[2]);
    }

    @Test
    void sellNiceTimingSuccess() {
        int[] prices = {3, 2, 1};
        int amount = 5;
        when(driver.getPrice("code"))
                .thenReturn(prices[0])
                .thenReturn(prices[1])
                .thenReturn(prices[2]);

        ats.sellNiceTiming("code", amount);
        verify(driver, times(3)).getPrice("code");
        verify(driver, times(1)).sell("code", amount, prices[2]);
    }

    @Test
    void sellNiceTimingNotExecuted() {
        int[] prices = {3, 4, 2};
        int amount = 5;
        when(driver.getPrice("code"))
                .thenReturn(prices[0])
                .thenReturn(prices[1])
                .thenReturn(prices[2]);

        ats.sellNiceTiming("code", amount);
        verify(driver, times(3)).getPrice("code");
        verify(driver, never()).sell("code", amount, prices[2]);
    }
}