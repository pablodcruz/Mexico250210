import com.revature.models.Animal;
import com.revature.models.HelloWorld;
import com.revature.models.Owner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        ApplicationContext contextXML = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld obj = (HelloWorld) contextXML.getBean("helloWorld");
        obj.getMessage();

        Animal animal1 = (Animal) contextXML.getBean("animal1");
        System.out.println(animal1);

        Animal animal2 = (Animal) contextXML.getBean("animal2");
        System.out.println(animal2);

        Owner owner = (Owner) contextXML.getBean("owner");
        System.out.println(owner);

        Owner owner2 = (Owner) contextXML.getBean("owner2");
        System.out.println(owner2);

        List<String> animalTypes = (ArrayList) contextXML.getBean("animals");
        System.out.println(animalTypes);

    }
}
