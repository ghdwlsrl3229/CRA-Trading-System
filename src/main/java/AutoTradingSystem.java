public class AutoTradingSystem {
    private StockerBrockerDriver stockerBrockerDriver;

    public void selectStockerBrocker(StockerBrockerDriver driver) {
        this.stockerBrockerDriver = driver;
    }

    public void buy(String code, int count, int price) {
        this.stockerBrockerDriver.buy(code, count, price);
    }

    public void getPrice(String stockCode) {
        if (stockerBrockerDriver == null) {
            throw new BrockerNotSelectedException("Please Select Before Do Something.");
        }
        stockerBrockerDriver.getPrice(stockCode);
    }

    public void sell(String stockCode, int count, int price) {
        this.stockerBrockerDriver.sell(stockCode, count, price);
    }

    public void login(String id, String pass) {
        if (id == null || pass == null) {
            System.out.println("로그인 실패. 입력된 계정 정보가 없습니다.");
            return;
        }
        this.stockerBrockerDriver.login(id, pass);
    }
}
