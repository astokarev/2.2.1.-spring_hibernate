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

      User user1 = new User("User1", "LastName1", "User1@mail.ru");
      User user2 = new User("User2", "LastName2", "User2@mail.ru");

      Car car1 = new Car("Car1", 111);
      Car car2 = new Car("Car2", 222);

      user1.setCar(car1);
      user2.setCar(car2);

      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Model = "+user.getCar().getModel());
         System.out.println("Series = "+user.getCar().getSeries());
         System.out.println();
      }

      try {
         User carUser = userService.getCarUser("Car1", 111);
         System.out.println(carUser);
      } catch (NoResultException e) {
         System.out.println("User отсутствует");
      }


      context.close();
   }
}
