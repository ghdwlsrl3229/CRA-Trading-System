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
    void sellTest() {
        String stockCode = "code";
        int count = 2;
        int price = 4;
        mockDriver.sell(stockCode, count, price);
    }

    @Test
    void sellTest_StockCodeIsNull() {
        String stockCode = null;
        int count = 2;
        int price = 4;
        assertThrows(RuntimeException.class,()-> {
            mockDriver.sell(stockCode, count, price);
        });
    }

    @Test
    void sellTest_CountIsNegative() {
        String stockCode = "code";
        int count = 0;
        int price = 4;
        assertThrows(RuntimeException.class,()-> {
            mockDriver.sell(stockCode, count, price);
        });
    }

    @Test
    void sellTest_PriceIsNegative() {
        String stockCode = "code";
        int count = 2;
        int price = -1;
        assertThrows(RuntimeException.class,()-> {
            mockDriver.sell(stockCode, count, price);
        });
    }

    @Test
    void getPriceTest() {
        assertEquals(0, mockDriver.getPrice("code"));
    }
}