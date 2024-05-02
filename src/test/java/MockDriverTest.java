import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MockDriverTest {
    MockDriver mockDriver;

    @BeforeEach
    void setUp() {
        mockDriver = new MockDriver();
    }

    @Test
    void createMockDriver() {
        assertNotNull(mockDriver);
    }

    @Test
    void loginTest() {
        mockDriver.login("id", "pass");
    }

    @Test
    void buyTest() {
        String stockCode = "code";
        int count = 2;
        int price = 3;
        mockDriver.buy(stockCode, count, price);
    }


    @Test
    void sellTest_loginFail() {
        assertThrows(RuntimeException.class,()-> {
            mockDriver.sell("code", 2, 4);
        });
    }

    @Test
    void sellTest_StockCodeIsNull() {
        assertThrows(RuntimeException.class,()-> {
            mockDriver.sell(null, 2, 4);
        });
    }

    @Test
    void sellTest_CountIsNegative() {
        assertThrows(RuntimeException.class,()-> {
            mockDriver.sell("code", 0, 4);
        });
    }

    @Test
    void sellTest_PriceIsNegative() {
        assertThrows(RuntimeException.class,()-> {
            mockDriver.sell("code", 2, -1);
        });
    }

    @Test
    void getPriceTest() {
        assertEquals(0, mockDriver.getPrice("code"));
    }
}