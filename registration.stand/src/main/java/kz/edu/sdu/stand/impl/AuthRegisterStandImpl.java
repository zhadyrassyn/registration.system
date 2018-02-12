package kz.edu.sdu.stand.impl;

import kz.edu.sdu.controller.model.AuthRequestInfo;
import kz.edu.sdu.controller.model.AuthResponseInfo;
import kz.edu.sdu.controller.register.AuthRegister;
import kz.edu.sdu.stand.email.EmailSender;
import kz.edu.sdu.stand.impl.model.UserDto;
import kz.edu.sdu.stand.jwt.JwtTokenUtil;
import kz.edu.sdu.stand.repository.TokenRepository;
import kz.edu.sdu.stand.repository.UserRepository;
import kz.edu.sdu.stand.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by daniyar on 11/02/18.
 */
@Component
public class AuthRegisterStandImpl implements AuthRegister {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public ResponseEntity<?> createAuthenticationToken(AuthRequestInfo request, Device device) {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email,
                        request.password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.email);
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        // Return the token
        return ResponseEntity.ok(new AuthResponseInfo(token));
    }

    @Override
    public AuthResponseInfo signup(AuthRequestInfo request) {
        UserDto user = userRepository.findByEmail(request.email);
        if(user == null) {
            String token = Utils.generateString();
            String link = String.format("http://localhost:8080/auth/token/%s", token);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.email);
            message.setSubject("Регистрация");
            message.setText("Здравствуйте. Для подтверждения регистрации нажите на ссылку ниже\n\n" + link);
            emailSender.sendMessage(message);

            userRepository.save(request.email, request.password);
        } else {

        }
        return new AuthResponseInfo("token");
    }

    @Override
    public String verifyToken(String token) {
        Long id = tokenRepository.getIdByToken(token);
        userRepository.setStatus(id, "Activated");
        return "verified";
    }
}
