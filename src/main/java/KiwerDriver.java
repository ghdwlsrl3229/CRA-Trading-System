public class KiwerDriver implements StockerBrockerDriver {
    private boolean isLogin;
    private KiwerAPI api;

    public KiwerDriver() {
        this.api = new KiwerAPI();
    }

    public boolean isLogIn() {
        return isLogin;
    }

    @Override
    public void login(String id, String pass){
        if(id.length() < 3 || pass.length() < 3) {
            System.out.println("잘못된 계정정보입니다.");
            return;
        }
        api.login(id, pass);
        setLogIn(true);
    }

    private void setLogIn(boolean b) {
        this.isLogin = b;
    }

    @Override
    public void buy(String stockCode, int count, int price) {
        if (!this.isLogin) {
            throw new IllegalStateException("로그인이 안되었습니다.");
        }
        if (stockCode == null) {
            throw new IllegalArgumentException("stock code 를 입력해주세요.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("price(금액)는 최소 1 이상의 값이어야 합니다.");
        }
        if (count <= 0) {
            throw new IllegalArgumentException("count(개수)는 최소 1 이상의 값이어야 합니다.");
        }

        api.buy(stockCode, count, price);
    }

    @Override
    public void sell(String stockCode, int count, int price) {
        if (!isLogin) throw new RuntimeException("login을 먼저 진행해주세요.");
        if (stockCode.equals(null)) throw new RuntimeException("StockCode를 입력해주세요.");
        if (count <= 0) throw new RuntimeException("수량을 1개 이상 입력해주세요.");
        if (price <= 0) throw new RuntimeException("가격은 0 이하가 될 수 없습니다.");

        api.sell(stockCode, count, price);
    }

    @Override
    public int getPrice(String stockCode) {
        if (stockCode == null || stockCode.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return api.currentPrice(stockCode);
    }

}
