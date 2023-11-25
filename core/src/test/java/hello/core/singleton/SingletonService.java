package hello.core.singleton;

public class SingletonService {

//    1. static 영역에 객체를 한 개 생성해둠
    private static final SingletonService instance = new SingletonService();

//    2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메소드를 통해서만 조회하도록 혀용
    public static SingletonService getInstance() {
        return instance;
    }

//    3. 생성자를 private로 선언해서 외부에서 new 키워드로 객체를 생성하지 못하도록 막음
    private SingletonService() {

    }


    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
