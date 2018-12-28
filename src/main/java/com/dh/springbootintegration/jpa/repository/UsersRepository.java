package com.dh.springbootintegration.jpa.repository;

import com.dh.springbootintegration.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

// 继承JpaSpecificationExecutor之后可以使用复杂的查询
public interface UsersRepository extends JpaSpecificationExecutor<User>, JpaRepository<User, Serializable> {
}
