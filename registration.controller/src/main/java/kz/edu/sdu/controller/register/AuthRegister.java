package kz.edu.sdu.controller.register;

import kz.edu.sdu.controller.model.AuthRequestInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;

/**
 * Created by daniyar on 11/02/18.
 */
public interface AuthRegister {
    ResponseEntity<?> createAuthenticationToken(AuthRequestInfo request, Device device);

}
