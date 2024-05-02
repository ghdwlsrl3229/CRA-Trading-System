import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KiwerDriverTest {
    KiwerDriver kiwerDriver;

    @BeforeEach
    void setUp() {
        kiwerDriver = new KiwerDriver();
    }

    @Test
    void createMockDriver() {
        assertNotNull(kiwerDriver);
    }

    @Test
    void loginTest_success(){
        kiwerDriver.login("ANY", "ANY");
        assertTrue(kiwerDriver.isLogIn());
    }
    @Test
    void loginTest_failed_id_short(){
        kiwerDriver.login("AN", "ANY");
        assertFalse(kiwerDriver.isLogIn());
    }
    @Test
    void loginTest_failed_pass_short(){
        kiwerDriver.login("ANY", "AN");
        assertFalse(kiwerDriver.isLogIn());
    }
    @Test
    void loginTest_failed_id_empty(){
        kiwerDriver.login("", "ANY");
        assertFalse(kiwerDriver.isLogIn());
    }
    @Test
    void loginTest_failed_pass_empty(){
        kiwerDriver.login("ANY", "");
        assertFalse(kiwerDriver.isLogIn());
    }

    @Test
    void buy_not_login() {
        assertThrows(IllegalStateException.class, () -> {
            kiwerDriver.buy("code", 2, 3);
        });
    }

    @Test
    void buy_invalid_price() {
        assertThrows(IllegalStateException.class, () -> {
            kiwerDriver.buy("code", 2, 0);
        });
    }

    @Test
    void buy_invalid_count() {
        assertThrows(IllegalStateException.class, () -> {
            kiwerDriver.buy("code", 0, 3);
        });
    }

    @Test
    void sellTest_loginFail() {
        assertThrows(RuntimeException.class, () -> {
            kiwerDriver.sell("code", 2, 4);
        });
    }

    @Test
    void sellTest_StockCodeIsNull() {
        assertThrows(RuntimeException.class, () -> {
            kiwerDriver.sell(null, 2, 4);
        });
    }

    @Test
    void sellTest_CountIsNegative() {
        assertThrows(RuntimeException.class, () -> {
            kiwerDriver.sell("code", 0, 4);
        });
    }

    @Test
    void sellTest_PriceIsNegative() {
        assertThrows(RuntimeException.class, () -> {
            kiwerDriver.sell("code", 2, -1);
        });
    }

    @Test
    void getPriceButStockCodeIsNull() {
        try {
            kiwerDriver.getPrice(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(kiwerDriver);
        }
    }

    @Test
    void getPriceButStockCodeIsEmpty() {
        try {
            kiwerDriver.getPrice("");
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(kiwerDriver);
        }
    }

    @Test
    void getPriceButStockCodeIsWitespace() {
        try {
            kiwerDriver.getPrice(" ");
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(kiwerDriver);
        }
    }
}