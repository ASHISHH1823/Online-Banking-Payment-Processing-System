package com.ashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.entity.Appuser;

public interface AuthRepo extends JpaRepository<Appuser, Long>{

}
