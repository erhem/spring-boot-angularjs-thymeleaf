package com.diputra.erhem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PracticeCrudApplication.class)
public class PracticeCrudApplicationTests {

	@Test
	public void contextLoads() {
	}
        
        /*
        @Autowired
    UserRepository userRepository;

    @Test
    public void testSelects() {

        User u = userRepository.findOne(1);
        System.out.println("User Id : " + u.getUserId());
        System.out.println("First Name : " + u.getFirstName());
        System.out.println("Last Name : " + u.getLastName());
        System.out.println("Username : " + u.getUsername());
        System.out.println("Birth of Date : " + u.getBirthOfDate());
        System.out.println("Password : " + u.getPassword());
        System.out.print("Roles : " );
        for (Role r : u.getRoles()) {
            System.out.print(r.getRoleName() + " ");
        }
        Assert.assertEquals("erhem", u.getUsername());
    }
        */

}
