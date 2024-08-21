package com.bookshop.repository.authentication;

import com.bookshop.entity.authentication.User;
import com.bookshop.entity.authentication.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long>, JpaSpecificationExecutor<Verification> {

    Optional<Verification> findByUserId(Long userId);

}