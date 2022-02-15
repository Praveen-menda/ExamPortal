package com.exam.service.impl;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepo;
import com.exam.repo.UserRepo;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepo.findByUsername(user.getUsername());
        if(local != null)
        {
            System.out.println("User Already Present");
            throw new UserFoundException();
        }else {
            for (UserRole ur:userRoles)
            {
                roleRepo.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local =this.userRepo.save(user);
        }
        return local;
    }

    @Override
    public User getByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
      userRepo.deleteById(id);
    }
}
