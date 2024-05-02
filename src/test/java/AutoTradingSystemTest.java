import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        ats.login("id", "pass");

        verify(driver, times(1)).login("id", "pass").;
    }

    @Test
    void buy_normal_test() {
        ats.buy("code", 2, 3);

        verify(driver, times(1)).buy("code", 2, 3);
    }

    @Test
    void buy_null_stock_code() {
        try {
            ats.buy(null, 2, 3);
        }
        catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("stock code 를 입력해주세요.");
        }
        verify(driver, times(1)).buy(null, 2, 3);
    }

    @Test
    void buy_invalid_count() {
        try {
            ats.buy("code", 0, 3);
        }
        catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("count(개수)는 최소 1 이상의 값이어야 합니다.");
        }
        verify(driver, times(1)).buy("code", 0, 3);
    }

    @Test
    void buy_invalid_price() {
        try {
            ats.buy("code", 2, 0);
        }
        catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("price(금액)는 최소 1 이상의 값이어야 합니다.");
        }
        verify(driver, times(1)).buy("code", 2, 0);
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
}
