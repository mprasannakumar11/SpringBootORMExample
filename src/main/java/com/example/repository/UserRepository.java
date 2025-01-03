package com.example.repository;

import com.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    //Query Methods
   // List<User> findUserList(String namestartwith);

    // Pagination and Sorting
    Page<User> findAll(Pageable pageable);
    List<User> findAll(Sort sort);

    //Custom Queries
    @Query("SELECT u FROM User u WHERE u.email=?1")
    User findByEmail(String email);
}

