package com.zebs.facturation.security.service;

import com.zebs.facturation.security.dao.UserDao;
import com.zebs.facturation.security.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return  org.springframework.security.core.userdetails.User.
                withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_USER").build();

    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(UUID id) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteById(UUID id) {

    }
}
