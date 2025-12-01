package cortefacil.unifor.controllers;

import cortefacil.unifor.models.DTOs.user.AuthenticationDTO;
import cortefacil.unifor.models.DTOs.user.LoginResponseDTO;
import cortefacil.unifor.models.DTOs.user.UserInsertDTO;
import cortefacil.unifor.models.entities.User;
import cortefacil.unifor.services.TokenService;
import cortefacil.unifor.services.UserService; // Opcional, se quiser registrar por aqui
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserInsertDTO data){
        // Reuse seu UserService.insert aqui se quiser um endpoint público de cadastro
        var newUser = userService.insert(data);
        return ResponseEntity.ok(newUser);
    }
}