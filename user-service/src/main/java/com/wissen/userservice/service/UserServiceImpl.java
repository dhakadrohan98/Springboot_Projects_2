package com.wissen.userservice.service;

import com.wissen.userservice.dto.DepartmentDto;
import com.wissen.userservice.dto.ResponseDto;
import com.wissen.userservice.dto.UserDto;
import com.wissen.userservice.entity.User;
import com.wissen.userservice.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private APIClient apiClient;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {

        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);
        //we are using RestTemplate to make a REST API call to department-service
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate
//                .getForEntity("http://localhost:8080/api/departments/" + user.getDepartmentId(),
//                        DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();
//        System.out.println(responseEntity.getStatusCode());

        String departmentId = user.getDepartmentId();
        DepartmentDto departmentDto = apiClient.getDepartmentById(Long.parseLong(departmentId));
        System.out.println(departmentDto);
        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);

        return responseDto;
    }

    private UserDto mapToUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
