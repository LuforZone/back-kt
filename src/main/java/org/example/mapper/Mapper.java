package org.example.mapper;

import org.example.entity.Result;
import org.example.entity.User;
import org.springframework.stereotype.Repository;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Repository
public class Mapper {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Result<User> getAllUsers(int page) {
        return null;
    }
}
