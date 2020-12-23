package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.VerificationToken;
import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import ftn.ktsnvt.culturalofferings.model.exceptions.PasswordsNotMatchException;
import ftn.ktsnvt.culturalofferings.model.exceptions.UserEmailAlreadyVerifiedException;
import ftn.ktsnvt.culturalofferings.model.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.dto.ChangeUserDataDTO;
import ftn.ktsnvt.culturalofferings.helper.CredentialsHelper;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ServiceInterface<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialsHelper credentialsHelper;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findOne(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException(
                    id,
                    User.class
            );
        }
        return optional.get();
    }

    public User findByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail);

        return user;
    }

    @Override
    public User create(User entity) {
        //hash password
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return userRepository.save(entity);
    }

    @Override
    public User update(User entity, Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException(id, User.class);
        }
        return userRepository.save(userRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException(id, User.class);
        }
        userRepository.delete(optional.get());
    }

    public User confirmRegistration(String token) {
        VerificationToken verificationToken = verificationTokenService.findOne(token);
        verificationToken.getUser().setEnabled(true);
        return userRepository.save(verificationToken.getUser());
    }

    public User registerUser(User entity, String url) {
        //hash password
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        //create VerificationToken
        //saving verification token will save user as well
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(entity);
        verificationToken = verificationTokenService.create(verificationToken);

        //prepare mail content
        String email = entity.getEmail();
        String subject = "Confirm registration";
        String message = url + "?token=" + verificationToken.getToken();

        //send mail, async function
        emailService.sendMail(email, subject, message);

        return verificationToken.getUser();
    }


    public void resendToken(String email, String url) {
        //only send verification email if the user exists and has not been registered
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        if (user.isEnabled()) {
            throw new UserEmailAlreadyVerifiedException(email);
        }

        //create VerificationToken
        //saving verification token will save user as well
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken = verificationTokenService.create(verificationToken);

        //prepare mail content
        String subject = "Confirm registration";
        String message = url + "?token=" + verificationToken.getToken();

        //send mail, async function
        emailService.sendMail(email, subject, message);

    }

    public void changePassword(String oldPassword, String newPassword) {
    	// get email from Security Context
        String email = credentialsHelper.getUserEmailFromToken();
        User user = findByEmail(email);
        
        // check if provided old password matches saved old password
        // if true set new password
        if(passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }
        else {
        	 throw new PasswordsNotMatchException(email);
        }
    }
    
    public void ChangePersonalData(String firstName, String lastName) {
    	// get email from Security Context
        String email = credentialsHelper.getUserEmailFromToken();
        User user = findByEmail(email);
        
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
    }
    
    public ChangeUserDataDTO getUserData() {
    	// get email from Security Context
        String email = credentialsHelper.getUserEmailFromToken();
        User user = findByEmail(email);
        
        return new ChangeUserDataDTO(
        			user.getFirstName(),
        			user.getLastName()
        		);
    }

}
