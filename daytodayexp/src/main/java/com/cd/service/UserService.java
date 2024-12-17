package com.cd.service;

import com.cd.dto.LoginDto;
import com.cd.dto.UserInputDto;
import com.cd.dto.UserOutputDto;
import com.cd.entity.User;
import com.cd.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public Optional<UserOutputDto> login(LoginDto loginDto) {

        Optional<User> user = userRepository.findByUserName(loginDto.getUserName());
        if (user.isPresent() && user.get().getPassword().equals(loginDto.getPassword())) {
             return Optional.of(convertToDto(user.get()));
        }
        return Optional.empty();
    }

    private UserOutputDto convertToDto(User user) {

        UserOutputDto dto = new UserOutputDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setUserName(user.getUserName());

        // Set other fields as necessary
        return dto;
    }

    public UserInputDto createUser(UserInputDto userInputDto) {
        User user = mapper.map(userInputDto, User.class);
        User savedUser  = userRepository.save(user);
        return mapper.map(savedUser , UserInputDto.class);
    }

    public List<UserOutputDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapper.map(user, UserOutputDto.class))
                .collect(Collectors.toList());
    }

    public Optional<UserOutputDto> getUserById(int id) {
        return userRepository.findById(id)
                .map(user -> mapper.map(user, UserOutputDto.class));
    }

    public UserInputDto updateUser(int id, UserInputDto userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName(userDetails.getUserName());
                    user.setEmail(userDetails.getEmail());
                    User updatedUser  = userRepository.save(user);
                    return mapper.map(updatedUser , UserInputDto.class);
                })
                .orElse(null);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}