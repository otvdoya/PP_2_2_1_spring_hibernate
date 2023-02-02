package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User11", "UserWithCar1", "car1@mail.ru", new Car("bmv", 7));
      User user2 = new User("User12", "UserWithCar2", "car2@mail.ru", new Car("lada", 6));
      User user3 = new User("User13", "UserWithCar3", "car3@mail.ru", new Car("audi", 3));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      userService.listUsers().forEach(user -> {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println("____________________");
      });

      context.close();
   }
}
