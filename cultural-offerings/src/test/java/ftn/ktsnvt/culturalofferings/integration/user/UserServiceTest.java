package ftn.ktsnvt.culturalofferings.integration.user;

import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.model.exceptions.*;
import ftn.ktsnvt.culturalofferings.repository.VerificationTokenRepository;
import ftn.ktsnvt.culturalofferings.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static ftn.ktsnvt.culturalofferings.integration.user.UserConstants.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    public void findAllTest(){
        assertEquals(LIST_SIZE, this.userService.findAll().size());
    }

    @Test
    public void findAllPageTest(){
        Page<User> userPage = this.userService.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE));
        assertEquals(PAGE_NUMBER, userPage.getNumber());
        assertEquals(3, userPage.getTotalPages());
        assertEquals(LIST_SIZE, userPage.getTotalElements());
    }

    @Test
    public void findOneTestFail(){
        assertThrows(EntityNotFoundException.class, () -> this.userService.findOne(NON_EXISTENT_ENTITY_ID));
    }

    @Test
    public void findOneTestSucceed(){
        User user = this.userService.findOne(EXISTING_ENTITY_ID);
        assertEquals(EXISTING_ENTITY_EMAIL, user.getEmail());
        assertEquals(EXISTING_ENTITY_FIRST_NAME, user.getFirstName());
        assertEquals(EXISTING_ENTITY_LAST_NAME, user.getLastName());
        assertEquals(EXISTING_ENTITY_USER_ROLE, user.getRole());
    }

    @Test
    public void findByEmailFail(){
        assertNull(this.userService.findByEmail("non existing email"));
    }

    @Test
    public void findByEmailSucceed(){
        User user = this.userService.findByEmail(EXISTING_ENTITY_EMAIL);
        assertEquals(EXISTING_ENTITY_ID, user.getId().longValue());
        assertEquals(EXISTING_ENTITY_FIRST_NAME, user.getFirstName());
        assertEquals(EXISTING_ENTITY_LAST_NAME, user.getLastName());
        assertEquals(EXISTING_ENTITY_USER_ROLE, user.getRole());
    }

    @Test
    @Transactional
    @Rollback
    public void create(){
        User arg = new User();
        arg.setEmail(NEW_ENTITY_EMAIL);
        arg.setPassword(NEW_ENTITY_PASSWORD);
        arg.setFirstName(NEW_ENTITY_FIRST_NAME);
        arg.setLastName(NEW_ENTITY_LAST_NAME);
        arg.setRole(NEW_ENTITY_USER_ROLE);

        assertEquals(LIST_SIZE , this.userService.findAll().size());

        User user = this.userService.create(arg);

        assertEquals(LIST_SIZE + 1, this.userService.findAll().size());
        assertEquals(NEW_ENTITY_EMAIL, user.getEmail());
        assertEquals(NEW_ENTITY_FIRST_NAME, user.getFirstName());
        assertEquals(NEW_ENTITY_LAST_NAME, user.getLastName());
        assertEquals(NEW_ENTITY_USER_ROLE, user.getRole());
    }

    @Test
    @Transactional
    @Rollback
    public void updateFailTest(){
        assertThrows(EntityNotFoundException.class, () -> this.userService.update(new User(), NON_EXISTENT_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void updateSucceedTest(){
        User arg = new User();
        arg.setId(UPDATE_ENTITY_ID);
        arg.setEmail(UPDATE_ENTITY_EMAIL);
        arg.setPassword(UPDATE_ENTITY_PASSWORD);
        arg.setFirstName(UPDATE_ENTITY_FIRST_NAME);
        arg.setLastName(UPDATE_ENTITY_LAST_NAME);

        User userBeforeUpdate = this.userService.findOne(UPDATE_ENTITY_ID);
        long commentSetSize = userBeforeUpdate.getComments().size();
        long subscriptionSetSize = userBeforeUpdate.getSubscriptions().size();

        assertNotNull(userBeforeUpdate.getComments().stream().filter(comment -> comment.getId().longValue() == REMOVE_COMMENT_ID).findFirst().orElse(null));
        assertNotNull(userBeforeUpdate.getSubscriptions().stream().filter(subscription -> subscription.getId().longValue() == REMOVE_SUBSCRIPTION_ID).findFirst().orElse(null));

        arg.setNews(userBeforeUpdate.getNews());
        arg.setComments(userBeforeUpdate.getComments().stream().filter(comment -> comment.getId().longValue() != REMOVE_COMMENT_ID).collect(Collectors.toSet()));
        arg.setSubscriptions(userBeforeUpdate.getSubscriptions().stream().filter(subscription -> subscription.getId().longValue() != REMOVE_SUBSCRIPTION_ID).collect(Collectors.toSet()));
        arg.setRatings(userBeforeUpdate.getRatings());

        User updatedUser = this.userService.update(arg, UPDATE_ENTITY_ID);

        assertEquals(UPDATE_ENTITY_EMAIL, updatedUser.getEmail());
        assertEquals(UPDATE_ENTITY_FIRST_NAME, updatedUser.getFirstName());
        assertEquals(UPDATE_ENTITY_LAST_NAME, updatedUser.getLastName());
        assertNull(updatedUser.getComments().stream().filter(comment -> comment.getId().longValue() == REMOVE_COMMENT_ID).findFirst().orElse(null));
        assertNull(updatedUser.getSubscriptions().stream().filter(subscription -> subscription.getId().longValue() == REMOVE_SUBSCRIPTION_ID).findFirst().orElse(null));
        assertEquals(updatedUser.getComments().size() + 1, commentSetSize);
        assertEquals(updatedUser.getSubscriptions().size() + 1, subscriptionSetSize);
    }

    @Test
    @Transactional
    @Rollback
    public void deleteTestFail(){
        assertThrows(EntityNotFoundException.class, () -> this.userService.delete(NON_EXISTENT_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void deleteTestSucceed(){
        assertNotNull(this.userService.findOne(DELETE_ENTITY_ID));
        this.userService.delete(DELETE_ENTITY_ID);
        assertThrows(EntityNotFoundException.class, () -> this.userService.findOne(DELETE_ENTITY_ID));
    }

    @Test
    @Transactional
    @Rollback
    public void confirmRegistrationFail(){
        User user = this.userService.findOne(ENABLE_ENTITY_ID);
        assertFalse(user.isEnabled());
        assertThrows(VerificationTokenNotFoundException.class, () -> this.userService.confirmRegistration(NON_EXISTENT_VERIFICATION_TOKEN_TEXT));
    }

    @Test
    @Transactional
    @Rollback
    public void confirmRegistrationTest(){
        User user = this.userService.findOne(ENABLE_ENTITY_ID);
        assertFalse(user.isEnabled());
        this.userService.confirmRegistration(EXISTING_VERIFICATION_TOKEN_TEXT);
        assertTrue(user.isEnabled());
    }

    @Test
    @Transactional
    @Rollback
    public void registerUserTestFail(){
        User arg1 = new User();
        arg1.setEmail(EXISTING_ENTITY_EMAIL);
        arg1.setPassword(EXISTING_ENTITY_PASSWORD);
        assertThrows(ModelConstraintViolationException.class, () -> this.userService.registerUser(arg1, ""));
    }

    @Test
    @Transactional
    @Rollback
    public void registerUserTestSucceed(){
        User arg1 = new User();
        arg1.setEmail(NEW_ENTITY_EMAIL);
        arg1.setPassword(NEW_ENTITY_PASSWORD);
        arg1.setFirstName(NEW_ENTITY_FIRST_NAME);
        arg1.setLastName(NEW_ENTITY_LAST_NAME);
        arg1.setRole(NEW_ENTITY_USER_ROLE);
        assertNull(this.userService.findByEmail(arg1.getEmail()));
        long verificationTokenListSize = this.verificationTokenRepository.findAll().size();

        User createdUser = this.userService.registerUser(arg1, "");

        assertNotNull(this.userService.findByEmail(arg1.getEmail()));
        assertFalse(createdUser.isEnabled());
        assertEquals(this.verificationTokenRepository.findAll().size(), verificationTokenListSize + 1);
    }

    @Test
    @Transactional
    @Rollback
    public void resendTokenFail1(){
        assertThrows(UserNotFoundException.class, () -> this.userService.resendToken(NON_EXISTENT_ENTITY_EMAIL, ""));
    }

    @Test
    @Transactional
    @Rollback
    public void resendTokenFail2(){
        assertThrows(UserEmailAlreadyVerifiedException.class, () -> userService.resendToken(EXISTING_ENTITY_EMAIL, ""));
    }

    @Test
    @Transactional
    @Rollback
    public void resendTokenSucceed(){
        long verificationTokenListSize = this.verificationTokenRepository.findByUserEmail(ENABLE_ENTITY_EMAIL).size();
        this.userService.resendToken(ENABLE_ENTITY_EMAIL, "");
        assertEquals(verificationTokenListSize + 1, this.verificationTokenRepository.findByUserEmail(ENABLE_ENTITY_EMAIL).size());
    }

    @Test
    @Transactional
    @Rollback
    public void changePasswordFail(){
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("mika.mika@gmail.com", "Mika"));
        assertThrows(PasswordsNotMatchException.class, () -> this.userService.changePassword("adsqw","qwe"));
    }

    @Test
    @Transactional
    @Rollback
    public void changePasswordSucceed(){
        String email = "mika.mika@gmail.com";
        String oldPassword = "Mika";
        String newPassword = "aeqwrqwrfq";
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(email, oldPassword));

        this.userService.changePassword(oldPassword, newPassword);
        User user = this.userService.findByEmail(email);

        assertFalse(passwordEncoder.matches(oldPassword, user.getPassword()));
        assertTrue(passwordEncoder.matches(newPassword, user.getPassword()));
    }
}
