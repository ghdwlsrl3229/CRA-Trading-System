public class NemoDriver implements StockerBrockerDriver{
    private boolean isLogin;

    public boolean isLogIn() {
        return isLogin;
    }

    @Override
    public void login(String id, String pass){
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
