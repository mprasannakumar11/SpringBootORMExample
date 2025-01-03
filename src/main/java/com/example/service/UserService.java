package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //Transaction Propagation
   /* REQUIRED	Uses existing transaction or creates a new one if none exists (default).
    REQUIRES_NEW	Always creates a new transaction, suspending any existing one.
    MANDATORY	Requires an existing transaction; throws an exception if none exists.
    SUPPORTS	Uses existing transaction if available; otherwise, executes without a transaction.
    NOT_SUPPORTED	Executes without a transaction, suspending any existing transaction.
    NEVER	Executes without a transaction; throws an exception if one exists.
    NESTED	Creates a nested transaction within an existing one or a new one if none exists.
    */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public User createUser(User user) {
        return userRepository.save(user);
    }

    //ISOLATION
    /*READ_UNCOMMITTED	Allows reading uncommitted changes by other transactions.	Dirty reads, non-repeatable reads, phantom reads
      READ_COMMITTED	Prevents reading uncommitted data by other transactions.	Non-repeatable reads, phantom reads
      REPEATABLE_READ	Ensures the same data is read again within the same transaction.	Phantom reads
      SERIALIZABLE	Ensures complete isolation from other transactions; highest level of isolation.	Performance impact due to locking and blocking
      */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Optional<User> finduser(Long id) {
        return userRepository.findById(id);
    }
    // Read-Only Transactions for Read Operations to optimise performance by allowing database optimisations.
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void updateUser(Long id, String name, String email) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(name);
            user.setEmail(email);
            userRepository.save(user);
        } else {
            throw new Exception("User not found with id: " + id);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void createAndUpdateUser(User user) throws Exception {
        // Create User
        createUser(user);
        // Update User
        updateUser(user.getId(), "Updated " + user.getName(), user.getEmail());
    }

}
