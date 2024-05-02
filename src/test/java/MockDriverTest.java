import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void buy_normal_test() {
        mockDriver.buy("code", 1, 1);
    }

    @Test
    void buy_not_login() {
        assertThrows(IllegalStateException.class, () -> {
            mockDriver.buy("code", 2, 3);
        });
    }

    @Test
    void buy_invalid_price() {
        assertThrows(IllegalStateException.class, () -> {
            mockDriver.buy("code", 2, 0);
        });
    }

    @Test
    void buy_invalid_count() {
        assertThrows(IllegalStateException.class, () -> {
            mockDriver.buy("code", 0, 3);
        });
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
