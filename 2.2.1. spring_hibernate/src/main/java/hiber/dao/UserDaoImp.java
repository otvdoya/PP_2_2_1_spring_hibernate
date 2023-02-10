package hiber.dao;

import hiber.model.User;
import hiber.model.Car;
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
   public User getUserByCar(Car car) {
      List<Car> cars = sessionFactory
              .getCurrentSession()
              .createQuery("from Car where model = :param1 and series = :param2")
              .setParameter("param1", car.getModel())
              .setParameter("param2", car.getSeries())
              .getResultList();
      return cars.size() == 0 ? null : cars.get(0).getOwner();
   }

}
