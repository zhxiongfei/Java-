import com.zxf.domain.Dog;
import com.zxf.domain.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Dog dog = (Dog) context.getBean("dog");
        Person person = (Person) context.getBean("person");

        System.out.println(dog);
        System.out.println(person);
    }
}
