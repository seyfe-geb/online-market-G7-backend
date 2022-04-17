package net.groupseven.onlinemarketg7backend.auth.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.groupseven.onlinemarketg7backend.auth.dto.AuthRequestDto;
import net.groupseven.onlinemarketg7backend.auth.dto.AuthResponseDto;
import net.groupseven.onlinemarketg7backend.config.security.JwtTokenUtil;
import net.groupseven.onlinemarketg7backend.user.dto.CreateLightUserDto;
import net.groupseven.onlinemarketg7backend.user.dto.UserDto;
import net.groupseven.onlinemarketg7backend.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Authentication")
@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @PostMapping({"login", "authenticate"})
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthRequestDto dto) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

            User user = (User) authenticate.getPrincipal();
            String token = jwtTokenUtil.generateAccessToken(user);

            AuthResponseDto res = modelMapper.map(user, AuthResponseDto.class);
            res.setToken(token);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(res);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register-buyer")
    public UserDto registerBuyer(@RequestBody @Valid CreateLightUserDto dto) {
        return userService.addBuyer(dto);
    }

    @PostMapping("register-seller")
    public UserDto registerSeller(@RequestBody @Valid CreateLightUserDto dto) {
        return userService.addSeller(dto);
    }
}
