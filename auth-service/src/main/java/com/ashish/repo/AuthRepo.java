package com.ashish.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.entity.Appuser;

public interface AuthRepo extends JpaRepository<Appuser, Long>{

	Optional<Appuser> findByUsername(String username);

	boolean existsByUsername(String username);

}
