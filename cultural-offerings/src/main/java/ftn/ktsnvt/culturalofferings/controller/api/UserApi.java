package ftn.ktsnvt.culturalofferings.controller.api;

import ftn.ktsnvt.culturalofferings.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/nvt-kts/cultural-offering")
public interface UserApi {

    @RequestMapping(value = "/user",
        produces = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> createUser(@RequestBody User body);


    @RequestMapping(value = "/user/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@PathVariable("username") String username);


    @RequestMapping(value = "/user/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<User> getUserByName(@PathVariable("username") String username);


    @RequestMapping(value = "/user/login",
        produces = { "application/json", "application/xml" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> loginUser(@RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password);


    @RequestMapping(value = "/user/logout",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Void> logoutUser();


    @RequestMapping(value = "/user/{username}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@PathVariable("username") String username, @RequestBody User body);

}
