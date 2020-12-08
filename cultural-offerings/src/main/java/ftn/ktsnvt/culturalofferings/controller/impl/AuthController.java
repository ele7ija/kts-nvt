package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.AuthApi;
import ftn.ktsnvt.culturalofferings.dto.LoginDTO;
import ftn.ktsnvt.culturalofferings.dto.RegisterDTO;
import ftn.ktsnvt.culturalofferings.helper.RegisterMapper;
import ftn.ktsnvt.culturalofferings.model.User;
import ftn.ktsnvt.culturalofferings.security.jwt.JwtConfig;
import ftn.ktsnvt.culturalofferings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController implements AuthApi
{
    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<RegisterDTO> register(RegisterDTO body) {
        User user = userService.create(registerMapper.toEntity(body));
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
}
