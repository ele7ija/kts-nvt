package ftn.ktsnvt.culturalofferings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements ServiceInterface<User> {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User create(User entity) throws Exception {
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity, Long id) throws Exception {
        User existingUser =  userRepository.findById(id).orElse(null);
        if(existingUser == null){
            throw new Exception("User with given id doesn't exist");
        }
        return userRepository.save(existingUser);
    }

    /*
    * Kada brišemo kategoriju kulturne ponude (institucija, manifestacija...),
    * obrisaće se i svi tipovi te kategorije (muzeji, festivali...).
    * */
    @Override
    public void delete(Long id) throws Exception {
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser == null){
            throw new Exception("User with given id doesn't exist");
        }
        userRepository.delete(existingUser);
    }
}
