public class AutoTradingSystem {
    private StockerBrockerDriver stockerBrockerDriver;

    public void selectStockerBrocker(StockerBrockerDriver driver) {
        this.stockerBrockerDriver = driver;
    }

    public void login(String id, String pass) {

    }

    public void buy(String code, int count, int price) {
        this.stockerBrockerDriver.buy(code, count, price);
    }

    public void sell(String code, int i, int i1) {
    }

    public int getPrice(String code) {
        return 0;
    }
}
