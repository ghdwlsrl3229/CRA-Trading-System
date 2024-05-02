import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NemoDriverTest {
    NemoDriver nemoDriver;

    @BeforeEach
    void setUp() {
        nemoDriver = new NemoDriver();
    }

    @Test
    void createMockDriver() {
        assertNotNull(nemoDriver);
    }

    @Test
    void loginTest_success(){
        nemoDriver.login("ANY", "ANY");
        assertTrue(nemoDriver.isLogIn());
    }
    @Test
    void loginTest_failed_id_short(){
        nemoDriver.login("AN", "ANY");
        assertFalse(nemoDriver.isLogIn());
    }
    @Test
    void loginTest_failed_pass_short(){
        nemoDriver.login("ANY", "AN");
        assertFalse(nemoDriver.isLogIn());
    }
    @Test
    void loginTest_failed_id_empty(){
        nemoDriver.login("", "ANY");
        assertFalse(nemoDriver.isLogIn());
    }
    @Test
    void loginTest_failed_pass_empty(){
        nemoDriver.login("ANY", "");
        assertFalse(nemoDriver.isLogIn());
    }

    @Test
    void buy_not_login() {
        assertThrows(IllegalStateException.class, () -> {
            nemoDriver.buy("code", 2, 3);
        });
    }

    @Test
    void buy_invalid_price() {
        assertThrows(IllegalStateException.class, () -> {
            nemoDriver.buy("code", 2, 0);
        });
    }

    @Test
    void buy_invalid_count() {
        assertThrows(IllegalStateException.class, () -> {
            nemoDriver.buy("code", 0, 3);
        });
    }

    @Test
    void sellTest_loginFail() {
        assertThrows(RuntimeException.class, () -> {
            nemoDriver.sell("code", 2, 4);
        });
    }

    @Test
    void sellTest_StockCodeIsNull() {
        assertThrows(RuntimeException.class, () -> {
            nemoDriver.sell(null, 2, 4);
        });
    }

    @Test
    void sellTest_CountIsNegative() {
        assertThrows(RuntimeException.class, () -> {
            nemoDriver.sell("code", 0, 4);
        });
    }

    @Test
    void sellTest_PriceIsNegative() {
        assertThrows(RuntimeException.class, () -> {
            nemoDriver.sell("code", 2, -1);
        });
    }

    @Test
    void getPriceButStockCodeIsNull() {
        try {
            nemoDriver.getPrice(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(nemoDriver);
        }
    }

    @Test
    void getPriceButStockCodeIsEmpty() {
        try {
            nemoDriver.getPrice("");
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(nemoDriver);
        }
    }

    @Test
    void getPriceButStockCodeIsWitespace() {
        try {
            nemoDriver.getPrice(" ");
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(nemoDriver);
        }
    }
}