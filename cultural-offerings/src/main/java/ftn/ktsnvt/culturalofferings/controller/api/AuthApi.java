package ftn.ktsnvt.culturalofferings.controller.api;

import ftn.ktsnvt.culturalofferings.dto.LoginDTO;
import ftn.ktsnvt.culturalofferings.dto.RegisterDTO;
import ftn.ktsnvt.culturalofferings.dto.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/auth")
public interface AuthApi {

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RegisterDTO> register(@RequestBody RegisterDTO body, BindingResult bindingResult, HttpServletRequest request);

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LoginDTO> login(HttpServletResponse response);

    @RequestMapping(value = "/confirm-registration", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> confirmRegistration(@RequestParam("token") String token);

    @RequestMapping(value = "/resend-token", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> resendToken(@RequestParam("email") String email, HttpServletRequest request);
}
