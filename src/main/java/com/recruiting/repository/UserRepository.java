package com.recruiting.repository;

import com.recruiting.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Marta Ginosyan
 * Date: 11/14/17
 */
@Primary
@Repository
public interface UserRepository extends BaseRepository<User> {

    User findByUsername(String username);

    List<User> findByDtypeNotOrderByCreatedAtDesc(String dtype);

}
