package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    public void disconnect() {

    }

//    의존관계 주입이 끝난 후 호출
    @PostConstruct
    public void init() {
        connect();
        call("초기화 연결 메시지");
    }

//    빈이 종료될 때 호출
    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}
