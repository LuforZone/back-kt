package org.example.mapper;

import org.example.entity.AdminAccount;
import org.example.entity.Result;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Repository
public class Mapper {
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;



    public User findByEmail(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        System.out.println("findUserMapper " + users.isEmpty() != null ? null : users.get(0));
        return users.isEmpty() ? null : users.get(0);
    }
    @Transactional
    public void save(User user) {
        System.out.println("saveUserMapper = " + user);
        entityManager.persist(user);
    }
@Transactional
    public void deleteByEmail(String email) {
    User user = findByEmail(email);
    System.out.println("deleteUserMapper = " + user);
    entityManager.remove(user);
    }

    @Transactional
    public void updateUser(User user,Integer id) {
        User existingUser = findById(id);
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        existingUser.setSex(user.getSex());
        existingUser.setPhone(user.getPhone());
        existingUser.setUuid(user.getUuid());
        entityManager.merge(existingUser);
    }

    private User findById(Integer id) {
        User usr = entityManager.find(User.class, id);
        System.out.println("aftet get by id result is " + usr);
        return usr;
    }

    public User getUserById(int id) {
return entityManager.find(User.class,id);
    }


    public AdminAccount findByAccountAndPassword(String account, String password) {
        TypedQuery<AdminAccount> query = entityManager.createQuery(
                "SELECT a FROM AdminAccount a WHERE a.account = :account AND a.password = :password",
                AdminAccount.class);
        query.setParameter("account", account);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

    public List<User> getAllUsers(int page) {
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("id"));

        Page<User> userPage = userRepository.findAll(pageRequest);
        return userPage.getContent();
    }

    public int getTotal() {
        //get how many datas in database
        return  (int) userRepository.count();
    }
}
