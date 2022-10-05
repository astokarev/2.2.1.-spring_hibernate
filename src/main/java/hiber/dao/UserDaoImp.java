package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getCarUser(String model, int series) {

      String hql = "from User user where user.car.model = ?1 and user.car.series = ?2";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter(1, model).setParameter(2, series);
      return query.setMaxResults(1).getSingleResult();
   }

}
