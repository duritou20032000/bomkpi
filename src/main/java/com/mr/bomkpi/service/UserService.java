package com.mr.bomkpi.service;

import com.mr.bomkpi.manager.CrossAppContext;
import com.mr.bomkpi.entity.User;
import com.mr.bomkpi.repository.UserRepository;
import com.mr.bomkpi.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    public User verify(String username, String password) {

        if (username == null || password == null) {
            return null;
        }

        User user = findByUsername(username);
        if (PasswordUtil.validatePasswd(password, user.getPassword())) {
            return user;
        }

        return null;
    }

    public void save(User user){
        userRepository.save(user);
    }

   public User  findByUsername(String username){
       return userRepository.findByUsername(username);
   }

//    public User findUserByNameFromCache(String username){
//        String key = "BM_USER_NAME@@" + username;
//        String cacheV = CrossAppContext.getInstance().getString(key);
//        if (cacheV != null){
//            return JSON.parseObject(cacheV, User.class);
//        }
//        return null;
//    }



}
