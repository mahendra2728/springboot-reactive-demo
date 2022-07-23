package com.pm.reactive.demo.service;

import com.pm.reactive.demo.model.UserDto;
import com.pm.reactive.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @PostConstruct
//    public void loadUsersData(){
//        List<User> users = IntStream.range(1,500)
//                .mapToObj(i -> new User(i,"first_name_"+i, "last_name_"+i, "email_"+i))
//                .collect(Collectors.toList());
//        userRepository.saveAll(users);
//    System.out.println("Users loaded successfully....");
//    }

    public Flux<UserDto> findAll(){
        return userRepository.findAll()
                .map(Convertor::entityToDto);
    }

    public Mono<UserDto> findById(Integer id){
        return userRepository.findById(id)
                .map(Convertor::entityToDto);
    }

    public Mono<UserDto> addUser(Mono<UserDto> userMono) {
        return userMono.map(Convertor::dtoToEntity)
                .flatMap(userRepository::insert)
                .map(Convertor::entityToDto);
    }

    public Mono<UserDto> updateUser(Mono<UserDto> userDto, Integer id) {
       return userRepository.findById(id)
                .flatMap(u-> userDto.map(Convertor::dtoToEntity))
                .doOnNext(u1 -> u1.setUserId(id))
                .flatMap(userRepository::save)
                .map(Convertor::entityToDto);
    }

}
