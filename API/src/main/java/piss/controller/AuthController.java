package piss.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import piss.DTOs.LoginDTO;
import piss.DTOs.RegisterUserDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import piss.DTOs.StudentDTO;
import piss.DTOs.TeacherDTO;
import piss.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;
    private final String secretKey = "SuperSecretKeyDontShowToAnyoneblablabla";
    private final AuthenticationManager authenticationManager;
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());

        try {
            Authentication authenticate = this.authenticationManager.authenticate(token);

            User principal = (User) authenticate.getPrincipal();

            List<String> role = principal
                    .getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            String jwt = JWT.create()
                    .withIssuer("localHost")
                    .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                    .withClaim("email", principal.getUsername())
                    .withClaim("roles", role)
                    .sign(algorithm);

            return ResponseEntity.ok(Map.of("token", jwt));
        }catch(BadCredentialsException badCredentialsException){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }


    @PostMapping("/register")
    public void register(@RequestBody RegisterUserDTO registerUserDTO) {

        this.userService.register(registerUserDTO);
    }

    @GetMapping("/user/{userEmail}/role")
    public String getRole(@PathVariable String userEmail) {
        return this.userService.getRoleByEmail(userEmail);
    }

    @PostMapping("/user/changeRole/{userEmail}/Student")
    public String changeRole(@PathVariable String userEmail, @RequestBody StudentDTO studentDTO) {
        return this.userService.changeRole(userEmail, studentDTO);
    }

    @PostMapping("/user/changeRole/{userEmail}/Teacher")
    public String changeRole(@PathVariable String userEmail, @RequestBody TeacherDTO teacherDTO) {
        return this.userService.changeRole(userEmail, teacherDTO);
    }

}
