package com.zebs.facturation.security.service;

import com.zebs.facturation.security.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    User save(User user);
    List<User> findAll();
    User findById(final UUID id);
    void delete(User user);
    void deleteById(UUID id);
}
