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
        mockDriver.login("id1", "pass1");

        assertEquals(1, mockDriver.getLoginCount());
    }

    @Test
    void buyTest() {
        String stockCode = "code";
        int count = 2;
        int price = 3;
        mockDriver.buy(stockCode, 2, 3);

        assertEquals(-count * price, mockDriver.getMoney());
        assertEquals(count, mockDriver.getStockCount(stockCode));
    }

    @Test
    void sellTest() {
        String stockCode = "code";
        int count = 2;
        int price = 4;
        mockDriver.sell("code", count, price);

        assertEquals(count * price, mockDriver.getMoney());
        assertEquals(-count, mockDriver.getStockCount(stockCode));
    }

    @Test
    void getPriceTest() {
        assertEquals(0, mockDriver.getPrice("code"));
        assertEquals(1, mockDriver.getLoginCount());
    }
}