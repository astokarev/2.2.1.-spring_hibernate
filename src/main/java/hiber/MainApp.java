package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Car1", 111);
      Car car2 = new Car("Car2", 222);

      User user1 = new User("User1", "LastName1", "User1@mail.ru", car1);
      User user2 = new User("User2", "LastName2", "User2@mail.ru", car2);



/*
      user1.setCar(car1);
      user2.setCar(car2);
*/

      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toString());
      }

      try {
         User carUser = userService.getCarUser(car1);
         System.out.println(carUser);
      } catch (NoResultException e) {
         System.out.println("User отсутствует");
      }


      context.close();
   }
}
