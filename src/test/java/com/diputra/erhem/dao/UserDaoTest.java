package com.diputra.erhem.dao;

import com.diputra.erhem.PracticeCrudApplication;
import com.diputra.erhem.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PracticeCrudApplication.class)
public class UserDaoTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void testFindUser() {
        User user1 = userRepository.findOne(3);
        Assert.assertEquals("allison", user1.getUserName());
        
        User user2 = userRepository.findOne(10);
        Assert.assertNotNull(user2);
        
    }
}
