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
    void getPriceTest() {
        assertEquals(0, mockDriver.getPrice("code"));
    }
}