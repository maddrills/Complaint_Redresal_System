package com.tryOne.AuthonBack.DAO.security;

import com.tryOne.AuthonBack.entity.complaint.Pincodes;
import com.tryOne.AuthonBack.entity.security.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(User theUSer) throws Exception {
        entityManager.persist(theUSer);
    }

    @Override
    @Transactional
    public void saveUserWithPinCode(User user){

        entityManager.createNativeQuery("INSERT INTO user(username, " +
                "password, enabled, phone_number, email, pincode_id) VALUES(?,?,?,?,?,?)")
                .setParameter(1,user.getUsername())
                .setParameter(2,user.getPassword())
                .setParameter(3,user.getEnabled())
                .setParameter(4,user.getPhoneNumber())
                .setParameter(5,user.getEmail())
                .setParameter(6,getPinCodeFromDb(user).getId())
                .executeUpdate();
    }

    @Override
    public Pincodes getPinCodeFromDb(User user){
        TypedQuery<Pincodes> query1 = entityManager.createQuery("SELECT p FROM Pincodes p " +
                "WHERE p.pinCode = :pinCode", Pincodes.class);

        query1.setParameter("pinCode",user.getPincodes().getPinCode());

        Pincodes pincodes = query1.getSingleResult();

        return pincodes;
    }

    @Override
    public User getAllUsers(int id) {

        TypedQuery<User> personId = entityManager.createQuery("SELECT u FROM User u " +
                "join fetch u.roles " +
                "where u.id = :userId",User.class);

        personId.setParameter("userId",id);

        return personId.getSingleResult();
    }

    @Override
    public User getUserByName(String uName) throws Exception {

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u " +
                "JOIN FETCH u.roles " +
                "where u.username = :userName ",User.class);

        query.setParameter("userName",uName);

        return query.getSingleResult();
    }



    @Override
    @Transactional
    public User updateUserPassword(User user,String Password) throws Exception{

        user.setPassword(Password);
        entityManager.merge(user);

        return user;
    }

}

