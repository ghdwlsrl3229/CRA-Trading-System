public class MockDriver implements StockerBrockerDriver {

    @Override
    public void login(String id, String pass) {

    }

    @Override
    public void buy(String stockCode, int count, int price) {

    }

    @Override
    public void sell(String stockCode, int count, int price) {

    }

    @Override
    public int getPrice(String stockCode) {
        return 0;
    }
}
