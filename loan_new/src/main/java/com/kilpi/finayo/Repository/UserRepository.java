package com.kilpi.finayo.Repository;


import com.kilpi.finayo.Domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer>{

    UserEntity findByUsername(String email);

}