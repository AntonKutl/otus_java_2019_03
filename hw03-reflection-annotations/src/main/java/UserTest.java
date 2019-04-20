
public class UserTest {

    @Before
    public void beforeMetod (){
        System.out.println("@Before анотация");
    }


    @Test
    public void testMetod (){
        System.out.println("@Test анотация");
    }

    @After
    public void afterMetod (){
        System.out.println("@After анотация");

    }
    @Test
    public void testMetod2 (){
        System.out.println("@Test анотация");
        System.out.println(1/0);
    }


}
