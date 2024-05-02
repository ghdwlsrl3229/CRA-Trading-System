public class AutoTradingSystem {

    public static final int MAX_BUY_STOCK_COUNT = 255;
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
        if (isNullAccountValue(id, pass)) {
            System.out.println("로그인 실패. 입력된 계정 정보가 없습니다.");
            return;
        }
        this.stockerBrockerDriver.login(id, pass);
    }

    private static boolean isNullAccountValue(String id, String pass) {
        return id == null || pass == null;
    }

    private int[] checkRisingStockPrices(String selectedCode) {
        int[] history = new int[3];

        for (int i = 0; i < 3; i += 1) {
            history[i] = this.stockerBrockerDriver.getPrice(selectedCode);
        }
        return history;
    }

    private boolean isRisingPrices(int[] history) {
        return history[0] < history[1] && history[1] < history[2];
    }
  
    public void buyNiceTiming(String code, int balance) {
        int[] history = checkRisingStockPrices(code);
        if (isRisingPrices(history)) {
            this.stockerBrockerDriver.buy(code, balance / history[2], history[2]);
        } else {
            throw new RuntimeException("가격이 올라가는 추세가 아니므로 구입하지 않았습니다.");
        }
    }

    public void sellNiceTiming(String code, int amount) {
        int beforePrice = this.stockerBrockerDriver.getPrice(code);
        int curPrice = 0;

        boolean checkNiceTiming = true;
        for (int cnt = 0; cnt < 2; cnt++) {
            curPrice = this.stockerBrockerDriver.getPrice(code);
            if (beforePrice <= curPrice) {
                checkNiceTiming = false;
                continue;
            }
            beforePrice = curPrice;
        }
        if (checkNiceTiming)
            sell(code, amount, curPrice);
    }
}
