package com.dh.springbootintegration.jpa.api;

import com.dh.springbootintegration.jpa.entity.User;
import com.dh.springbootintegration.jpa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Log4j2
@Api(description = "用户接口")
public class UserApi {

    @Autowired
    private UserService service;

    @ApiOperation(value = "添加用户", notes = "根据给定的用户信息添加一个新用户",response = ResponseEntity.class,httpMethod = "PATCH")
    @RequestMapping(value = "/addUser",method = RequestMethod.PATCH)
    public ResponseEntity<User> addUser(final User user) {
        log.info("执行添加用户操作");
        return service.addUser(user);
    }

    @ApiOperation(value = "更新用户", notes = "根据给定的用户信息修改用户",response = ResponseEntity.class,httpMethod = "POST")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(final User user) {
        log.info("执行修改用户操作");
        return service.updateUser(user);
    }

    @ApiOperation(value = "删除用户", notes = "根据给定的用户ID删除一个用户",response = ResponseEntity.class,httpMethod = "DELETE")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(final int useId) {
        log.info("执行删除用户操作");
        return service.deleteUser(useId);
    }

    @ApiOperation(value = "查询用户", notes = "根据给定的用户ID获取一个用户",response = ResponseEntity.class,httpMethod = "GET")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(final int useId) {
        log.info("执行查询单个用户操作");
        return service.getUser(useId);
    }

    @ApiOperation(value = "查询用户", notes = "根据给定的用户信息查询用户",response = ResponseEntity.class,httpMethod = "POST")
    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    public ResponseEntity<List<User>> getUsers(final User user) {
        log.info("根据条件查询用户");
        return service.getUsers(user);
    }

    @ApiOperation(value = "分页查询用户", notes = "根据给定的用户信息查询用户",response = ResponseEntity.class,httpMethod = "POST")
    @RequestMapping(value = "/getUserPage", method = RequestMethod.POST)
    public ResponseEntity<Page<User>> getUserPage(final int pageId, final int pageSize) {
        log.info("分页查询用户");
        return service.getUserPage(pageId, pageSize);
    }
}
