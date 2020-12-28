package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.CustomUserDetails;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null || !user.isEnabled()){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }else{
            return new CustomUserDetails(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole().getGrantedAuthorities(),
                    user.getRole(),
                    user.isEnabled());
        }
    }
}
