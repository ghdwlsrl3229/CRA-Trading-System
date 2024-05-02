public class AutoTradingSystem {
    private StockerBrockerDriver stockerBrockerDriver;

    public void selectStockerBrocker(StockerBrockerDriver driver) {
        this.stockerBrockerDriver = driver;
    }

    public void sell(String stockCode, int count, int price) {
        this.stockerBrockerDriver.sell(stockCode, count, price);
    }
}
