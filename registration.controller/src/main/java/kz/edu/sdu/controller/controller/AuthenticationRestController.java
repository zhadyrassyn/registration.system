package kz.edu.sdu.controller.controller;

import kz.edu.sdu.controller.model.AuthRequestInfo;
import kz.edu.sdu.controller.register.AuthRegister;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
