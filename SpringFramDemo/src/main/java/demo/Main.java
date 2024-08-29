package demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//1:15:32
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        ShoppingCart cart = context.getBean(ShoppingCart.class);
        cart.checkout("Adding product");
    }
}

//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        Staff staff = context.getBean(Nurse.class);
//        staff.assist();

//    Nurse nurse = (Nurse) context.getBean("nurse1");
//        doctor.assist();
//        nurse.assist();

//        Staff staff = context.getBean(Nurse.class);
//        staff.assist();