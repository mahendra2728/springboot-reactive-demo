package com.pm.reactive.demo.resources;

import com.pm.reactive.demo.model.UserDto;
import com.pm.reactive.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/api/users")
public class UserResource {

  @Autowired private UserService userService;

  @GetMapping
  public Flux<UserDto> findAll() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public Mono<UserDto> findById(@PathVariable("id") Integer id){
    return userService.findById(id);
  }

  @PostMapping
  public Mono<UserDto> addUser(@RequestBody Mono<UserDto> userDtoMono) {
    return userService.addUser(userDtoMono);
  }

  @PutMapping("/{id}")
  public Mono<UserDto> updateUser(
      @RequestBody Mono<UserDto> userDto, @PathVariable("id") Integer id) {
    return userService.updateUser(userDto, id);
  }
}
