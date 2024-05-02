public interface StockerBrockerDriver {

    void login(String id, String pass);
    void buy(String stockCode, int count , int price);
    void sell( String stockCode , int count , int price);
    int getPrice (String stockCode);
    void setLogIn(boolean isLogin);
}
