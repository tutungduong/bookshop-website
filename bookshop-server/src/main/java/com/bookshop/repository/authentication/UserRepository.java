package com.bookshop.repository.authentication;

import com.bookshop.entity.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);

    Optional<User> findByEmailAndResetPasswordToken(String email, String resetPasswordToken);
}
