package kz.edu.sdu.controller.controller;

import kz.edu.sdu.controller.model.AuthRequestInfo;
import kz.edu.sdu.controller.model.AuthResponseInfo;
import kz.edu.sdu.controller.register.AuthRegister;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * Created by daniyar on 11/02/18.
 */
@RestController
public class AuthenticationRestController {

    private AuthRegister authRegister;

    public AuthenticationRestController(AuthRegister authRegister) {
        this.authRegister = authRegister;
    }

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequestInfo request, Device device) throws AuthenticationException {
        return authRegister.createAuthenticationToken(request, device);
    }

    @RequestMapping(value = "auth/signup", method = RequestMethod.POST)
    public AuthResponseInfo signup(@RequestBody AuthRequestInfo request) {
        return authRegister.signup(request);
    }

    @RequestMapping(value = "auth/token/{token}")
    public String verifyUser(@PathVariable("token")String token) {
        return authRegister.verifyToken(token);
    }
}
