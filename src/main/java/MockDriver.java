public class MockDriver implements StockerBrockerDriver {

    public static final int MIN_ID_LENGTH = 3;
    public static final int MIN_PASS_LENGTH = 3;
    private boolean isLogin;

    @Override
    public void login(String id, String pass){
        if(isInvalidAccount(id, pass)) {
            System.out.println("잘못된 계정 정보입니다. 로그인 실패");
            return;
        }
        System.out.println(id + "님 로그인 성공");
        setLogIn(true);
    }

    public boolean isInvalidAccount(String id, String pass) {
        return id.length() < MIN_ID_LENGTH || pass.length() < MIN_PASS_LENGTH;
    }

    private void setLogIn(boolean isLogin) {
        this.isLogin = isLogin;
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
    }

    @Override
    public void sell(String stockCode, int count, int price) {
        if (!isLogin) throw new RuntimeException("login을 먼저 진행해주세요.");
        if (stockCode.equals(null)) throw new RuntimeException("StockCode를 입력해주세요.");
        if (count <= 0) throw new RuntimeException("수량을 1개 이상 입력해주세요.");
        if (price <= 0) throw new RuntimeException("가격은 0 이하가 될 수 없습니다.");

    }

    @Override
    public int getPrice(String stockCode) {
        if (stockCode == null || stockCode.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return 0;
    }

    public boolean isLogIn() {
        return isLogin;
    }
}
