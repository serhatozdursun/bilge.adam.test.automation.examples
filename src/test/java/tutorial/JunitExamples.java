package tutorial;


import org.junit.jupiter.api.*;

public class JunitExamples {

    @BeforeAll
    public static void beforeAll() {
        // Before all tum testlerden once bir kez calir
        System.out.println("beforeAll");
    }

    @BeforeEach
    public void beforeEach() {
        //Tarayici ayaga kaldirdigimiz kisim
        // mevcut butun testlerden once birerkez caliir.
        // or: eger 100 tane test varsa her testen once 1 kez yani 100 kez calisr
        System.out.println("beforeEach");
    }

    @Test
    public void test1() {
        System.out.println("test 1");
    }

    @Test
    public void test2() {
        System.out.println("test 2");
    }

    @AfterEach
    public void afterEach() {
        //Tarayici ayaga kapatacagimiz kisim
        // mevcut butun testlerden sonra birer kez caliir.
        // or: eger 100 tane test varsa her testen sonra 1 kez yani 100 kez calisr
        System.out.println("afterEach");
    }

    @AfterAll
    public static void afterAll() {
        // After Each tum testlerden sonra bir kez calir
        System.out.println("afterAll");
    }
}
