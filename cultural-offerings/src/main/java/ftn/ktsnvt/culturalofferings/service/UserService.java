package ftn.ktsnvt.culturalofferings.service;

import ftn.ktsnvt.culturalofferings.model.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
    public User create(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity, Long id) {
        Optional<User> optional =  userRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(id, User.class);
        }
        return userRepository.save(userRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        Optional<User> optional =  userRepository.findById(id);
        if(optional.isEmpty()){
            throw new EntityNotFoundException(id, User.class);
        }
        userRepository.delete(optional.get());
    }
}
