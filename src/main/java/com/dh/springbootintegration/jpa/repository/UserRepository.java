package com.dh.springbootintegration.jpa.repository;

import com.dh.springbootintegration.jpa.entity.User;
import com.dh.springbootintegration.jpa.enums.UseSex;
import com.dh.springbootintegration.jpa.enums.UseState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Serializable> {
    @Modifying
    @Query(value = "update USER set USE_STATE= ?1 WHERE USE_ID= ?2", nativeQuery = true)
    void updateUser(UseState state, int useId);

       @Modifying
    @Query(value = "update USER set USE_PHONE_NUM = :num WHERE USE_ID= :useId", nativeQuery = true)
    void updateUsePhoneNum(@Param(value = "num") String num, @Param(value = "useId") int useId);

    List<User> findByUseNameLike(final String useName);
    List<User> findByUseAge(final int useAge);
    List<User> findByUseSex(final UseSex useSex);
    List<User> findByUseState(final UseState useState);
}
