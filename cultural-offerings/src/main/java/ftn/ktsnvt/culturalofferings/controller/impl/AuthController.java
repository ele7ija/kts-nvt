package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.AuthApi;
import ftn.ktsnvt.culturalofferings.dto.LoginDTO;
import ftn.ktsnvt.culturalofferings.dto.RegisterDTO;
import ftn.ktsnvt.culturalofferings.dto.UserDTO;
import ftn.ktsnvt.culturalofferings.helper.RegisterMapper;
import ftn.ktsnvt.culturalofferings.helper.UserMapper;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.model.exceptions.RequestBodyBindingFailedException;
import ftn.ktsnvt.culturalofferings.security.jwt.JwtConfig;
import ftn.ktsnvt.culturalofferings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AuthController implements AuthApi
{
    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<RegisterDTO> register(@Valid RegisterDTO body, BindingResult bindingResult, HttpServletRequest request) {
        if(bindingResult.hasErrors()){
            throw new RequestBodyBindingFailedException(
                    bindingResult.getFieldErrors().get(0).getField(),
                    bindingResult.getFieldErrors().get(0).getDefaultMessage(),
                    RegisterDTO.class
            );
        }
        User user = userService.registerUser(
                registerMapper.toEntity(body),
                request.getRequestURL()
                        .replace(
                            request.getRequestURL().indexOf("/register"),
                            request.getRequestURL().toString().length(),
                        "/confirm-registration"
                        )
                        .toString()
                );
        return new ResponseEntity<>(
                registerMapper.toDto(user),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<LoginDTO> login(HttpServletResponse response) {
        String jwt = response.getHeader(jwtConfig.getAuthorizationHeader()).replace(jwtConfig.getTokenPrefix(), "");
        return new ResponseEntity<>(
                new LoginDTO(jwt),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<UserDTO> confirmRegistration(String token) {
        return new ResponseEntity<>(
                userMapper.toDto(userService.confirmRegistration(token)),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Void> resendToken(String email, HttpServletRequest request) {
        userService.resendToken(
                email,
                request.getRequestURL()
                    .replace(
                            request.getRequestURL().indexOf("/resend-token"),
                            request.getRequestURL().toString().length(),
                            "/confirm-registration"
                    )
                    .toString()
        );
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }
}
