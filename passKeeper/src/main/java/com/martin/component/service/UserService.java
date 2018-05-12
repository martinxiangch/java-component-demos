package com.martin.component.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martin.component.model.User;
import com.martin.component.repository.UserRepository;
import com.martin.component.util.MongoUtil;

@Service
public class UserService {
   
    @Autowired
   private UserRepository userRepository;
   
    @Autowired
    private MongoUtil mongoUtil;
    
    private static final String  USER_SEQ="seq_user";
    
    public void Insert(User user) {
        if(user!=null) {
            user.setUid(mongoUtil.getNextSequence(USER_SEQ));
        }
        userRepository.insert(user);
    }
    
    public List<User> GetAll() {
        return userRepository.findAll();
    }
    
    public void Update(User user) {
    	userRepository.save(user);
    }
    
    public void Delete(int id) {
    	userRepository.deleteById(id);
    }
}
    

