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
    }

    @Test
    void getPriceTest() {
    }
}
