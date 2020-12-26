package ftn.ktsnvt.culturalofferings.unit.user;

import ftn.ktsnvt.culturalofferings.dto.ChangeUserDataDTO;
import ftn.ktsnvt.culturalofferings.helper.CredentialsHelper;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.model.VerificationToken;
import ftn.ktsnvt.culturalofferings.model.exceptions.PasswordsNotMatchException;
import ftn.ktsnvt.culturalofferings.model.exceptions.UserEmailAlreadyVerifiedException;
import ftn.ktsnvt.culturalofferings.model.exceptions.UserNotFoundException;
import ftn.ktsnvt.culturalofferings.repository.UserRepository;
import ftn.ktsnvt.culturalofferings.service.EmailServiceImpl;
import ftn.ktsnvt.culturalofferings.service.UserService;
import ftn.ktsnvt.culturalofferings.service.VerificationTokenService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static ftn.ktsnvt.culturalofferings.integration.user.UserConstants.EXISTING_ENTITY_FIRST_NAME;
import static ftn.ktsnvt.culturalofferings.integration.user.UserConstants.EXISTING_ENTITY_LAST_NAME;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private VerificationTokenService verificationTokenService;

    @Mock
    private EmailServiceImpl emailService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CredentialsHelper credentialsHelper;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void confirmRegistration(){
        String arg = "Verification token string";
        VerificationToken verificationTokenStub = new VerificationToken();
        verificationTokenStub.setUser(new User());

        when(verificationTokenService.findOne(arg)).thenReturn(verificationTokenStub);
        when(userRepository.save(verificationTokenStub.getUser())).thenReturn(verificationTokenStub.getUser());

        User user = userService.confirmRegistration(arg);

        assertTrue(verificationTokenStub.getUser().isEnabled());
    }

    @Test
    public void resendTokenFail1(){
        String emailArg = "user email";
        String urlArg = "url";

        when(userRepository.findByEmail(emailArg)).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> userService.resendToken(emailArg, urlArg));
    }

    @Test
    public void resendTokenFail2(){
        String emailArg = "user email";
        String urlArg = "url";

        User userStub = new User();
        userStub.setEnabled(true);

        when(userRepository.findByEmail(emailArg)).thenReturn(userStub);

        assertThrows(UserEmailAlreadyVerifiedException.class, () -> userService.resendToken(emailArg, urlArg));
    }

    @Test
    public void changePasswordFail(){
        String oldPassword = "old password";
        String newPassword = "new password";

        String emailStub = "email stub";
        User userStub = new User();

        when(credentialsHelper.getUserEmailFromToken()).thenReturn(emailStub);
        when(userService.findByEmail(emailStub)).thenReturn(userStub);
        when(passwordEncoder.matches(oldPassword, userStub.getPassword())).thenReturn(false);

        assertThrows(PasswordsNotMatchException.class, () -> userService.changePassword(oldPassword, newPassword));
    }

    @Test
    public void getUserData(){
        String emailStub = "email stub";
        User userStub = new User();
        userStub.setFirstName(EXISTING_ENTITY_FIRST_NAME);
        userStub.setLastName(EXISTING_ENTITY_LAST_NAME);

        when(credentialsHelper.getUserEmailFromToken()).thenReturn(emailStub);
        when(userService.findByEmail(emailStub)).thenReturn(userStub);

        ChangeUserDataDTO changeUserDataDTO = userService.getUserData();
        assertEquals(EXISTING_ENTITY_FIRST_NAME, changeUserDataDTO.getFirstName());
        assertEquals(EXISTING_ENTITY_LAST_NAME, changeUserDataDTO.getLastName());
    }
}
