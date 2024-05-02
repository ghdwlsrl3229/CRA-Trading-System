public class NemoDriver implements StockerBrockerDriver{
    public static final int MIN_ID_LENGTH = 3;
    public static final int MIN_PASS_LENGTH = 3;
    private boolean isLogin;
    NemoAPI api;
    public NemoDriver(){
        api = new NemoAPI();
    }
    public boolean isLogIn() {
        return isLogin;
    }

    @Override
    public void login(String id, String pass){
        if(id.length() < MIN_ID_LENGTH || pass.length() < MIN_PASS_LENGTH) {
            System.out.println("w잘못된 계정정보입니다.");
            return;
        }
        api.certification(id, pass);
        System.out.println(id + "님 로그인 성공");
        setLogIn(true);
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

    public void setLogIn(boolean isLogin) {
        this.isLogin = isLogin;
    }
}
