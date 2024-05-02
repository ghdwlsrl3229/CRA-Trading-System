public class AutoTradingSystem {
    private StockerBrockerDriver stockerBrockerDriver;

    public void selectStockerBrocker(StockerBrockerDriver driver) {
        this.stockerBrockerDriver = driver;
    }

    public void getPrice(String stockCode) {
        if(stockerBrockerDriver == null) {
            throw new BrockerNotSelectedException("Please Select Before Do Something.");
        }
        stockerBrockerDriver.getPrice(stockCode);
    }
}