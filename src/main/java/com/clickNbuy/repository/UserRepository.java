package com.clickNbuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.clickNbuy.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	boolean existsByMobile(Long mobile);

	Optional<User> findByEmail(String email);



	

}
