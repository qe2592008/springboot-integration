package com.dh.springbootintegration.jpa.service;

import com.dh.springbootintegration.jpa.entity.User;
import com.dh.springbootintegration.jpa.enums.UseSex;
import com.dh.springbootintegration.jpa.enums.UseState;
import com.dh.springbootintegration.jpa.repository.UserRepository;
import com.dh.springbootintegration.jpa.repository.UsersRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    public ResponseEntity<User> addUser(final User user) {
        return new ResponseEntity<>(repository.save(user),HttpStatus.OK);
    }

    public ResponseEntity<User> updateUser(final User user) {
        repository.updateUser(user.getUseState(), user.getUseId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<User> updateUsePhoneNum(final User user) {
        repository.updateUsePhoneNum(user.getUsePhoneNum(), user.getUseId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<User> deleteUser(final int useId) {
        return updateUser(User.builder().useState(UseState.CANCLE).useId(useId).build());
    }

    public ResponseEntity<User> getUser(final int useId) {
        return ResponseEntity.ok(repository.findById(useId).get());
    }

    public ResponseEntity<List<User>> getUsers(final User user) {
        if(!StringUtils.isEmpty(user.getUseName())){
            return new ResponseEntity<>(repository.findByUseNameLike(user.getUseName()), HttpStatus.OK);
        } else if(user.getUseAge() != 0){
            return new ResponseEntity<>(repository.findByUseAge(user.getUseAge()),HttpStatus.OK);
        } else if(Objects.nonNull(user.getUseSex())){
            return new ResponseEntity<>(repository.findByUseSex(user.getUseSex()),HttpStatus.OK);
        } else if(Objects.nonNull(user.getUseState())){
            return new ResponseEntity<>(repository.findByUseState(user.getUseState()),HttpStatus.OK);
        }
        return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<List<User>> getUserList(final User user) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
        List<User> users = repository.findAll(Example.of(user, matcher));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<Page<User>> getUserPage(final int pageId) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "useId"));
        Page<User> users = repository.findAll(new PageRequest(pageId,2, sort));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 使用Specification来实现复杂查询,使用Predicate
    public ResponseEntity<Page<User>> getUserPage(final int pageId, final int pageSize){
        Pageable pageable = new PageRequest(pageId, pageSize);
        return ResponseEntity.ok(usersRepository.findAll((root, query, cb) -> {
            Predicate p1 = cb.equal(root.get("useSex"), UseSex.MAN);
            Predicate p2 = cb.or(
                    cb.equal(root.get("useState"),UseState.COMMON),
                    cb.equal(root.get("useState"),UseState.FREEZE));
            query.where(p1, p2).orderBy(cb.asc(root.get("createTime")));
            return null;
        }, pageable));
    }

    // 使用纯Hibernate方式实现复杂查询
    @Autowired
    private EntityManager entityManager;
    public ResponseEntity<Page<User>> getUserList(final int pageId, final int pageSize){
        Pageable pageable = new PageRequest(pageId, pageSize);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        CriteriaQuery<Tuple> criteriaCountQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<User> root = criteriaQuery.from(User.class);
        Root<User> countRoot = criteriaCountQuery.from(User.class);
        Predicate p1 = criteriaBuilder.equal(root.get("useSex"), UseSex.MAN);
        Predicate p2 = criteriaBuilder.or(
                criteriaBuilder.equal(root.get("useState"),UseState.COMMON),
                criteriaBuilder.equal(root.get("useState"),UseState.FREEZE));
        criteriaQuery
                .where(p1,p2)
                .distinct(true)
                .select(root)
                .orderBy(criteriaBuilder.asc(root.get("createTime")));
        criteriaCountQuery
                .where(p1,p2)
                .distinct(true)
                .multiselect(criteriaBuilder
                        .count(countRoot.get("useId"))
                        .alias("total"));
        Query query = entityManager.createQuery(criteriaQuery);
        Query countQuery = entityManager.createQuery(criteriaCountQuery);
        query.setMaxResults(pageSize);
        query.setFirstResult(pageId * pageSize);
        List<User> users = query.getResultList();
        List<Tuple> tuples = countQuery.getResultList();
        Tuple tu = tuples.get(0);
        Long count = (Long)tu.get("total");
        Page<User> userPage = new PageImpl<User>(users, pageable,count);
        return ResponseEntity.ok(userPage);
    }

}
