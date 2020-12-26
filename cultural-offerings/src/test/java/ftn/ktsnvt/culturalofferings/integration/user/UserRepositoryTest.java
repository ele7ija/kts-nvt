package ftn.ktsnvt.culturalofferings.integration.user;

import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static ftn.ktsnvt.culturalofferings.integration.user.UserConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByEmailFail(){
        User user = this.userRepository.findByEmail(NON_EXISTENT_ENTITY_EMAIL);
        assertNull(user);
    }

    @Test
    public void findByEmailSucceed(){
        User user = this.userRepository.findByEmail(EXISTING_ENTITY_EMAIL);
        assertNotNull(user);
        assertEquals(EXISTING_ENTITY_EMAIL, user.getEmail());
        assertEquals(EXISTING_ENTITY_FIRST_NAME, user.getFirstName());
        assertEquals(EXISTING_ENTITY_LAST_NAME, user.getLastName());
        assertEquals(EXISTING_ENTITY_USER_ROLE, user.getRole());
        assertTrue(user.isEnabled());
    }
}
