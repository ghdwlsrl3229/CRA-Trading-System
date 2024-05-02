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
    void loginTest_success(){
        mockDriver.login("ANY", "ANY");
        assertTrue(mockDriver.isLogIn());
    }
    @Test
    void loginTest_failed_id_short(){
        mockDriver.login("AN", "ANY");
        assertFalse(mockDriver.isLogIn());
    }
    @Test
    void loginTest_failed_pass_short(){
        mockDriver.login("ANY", "AN");
        assertFalse(mockDriver.isLogIn());
    }
    @Test
    void loginTest_failed_id_empty(){
        mockDriver.login("", "ANY");
        assertFalse(mockDriver.isLogIn());
    }
    @Test
    void loginTest_failed_pass_empty(){
        mockDriver.login("ANY", "");
        assertFalse(mockDriver.isLogIn());
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
    void sellTest_loginFail() {
        assertThrows(RuntimeException.class, () -> {
            mockDriver.sell("code", 2, 4);
        });
    }

    @Test
    void sellTest_StockCodeIsNull() {
        assertThrows(RuntimeException.class, () -> {
            mockDriver.sell(null, 2, 4);
        });
    }

    @Test
    void sellTest_CountIsNegative() {
        assertThrows(RuntimeException.class, () -> {
            mockDriver.sell("code", 0, 4);
        });
    }

    @Test
    void sellTest_PriceIsNegative() {
        assertThrows(RuntimeException.class, () -> {
            mockDriver.sell("code", 2, -1);
        });
    }

    @Test
    void getPriceButStockCodeIsNull() {
        try {
            mockDriver.getPrice(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(mockDriver);
        }
    }

    @Test
    void getPriceButStockCodeIsEmpty() {
        try {
            mockDriver.getPrice("");
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(mockDriver);
        }
    }

    @Test
    void getPriceButStockCodeIsWitespace() {
        try {
            mockDriver.getPrice(" ");
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(mockDriver);
        }
    }

    @Test
    void getPriceTest() {
    }
}
