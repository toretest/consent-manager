package no.senseon.consentmanager.restcontroller;

import no.senseon.consentmanager.exception.AppForbiddenException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("error")
public class AccessDeniedRestController {

    @GetMapping("/deny")
    public void accessDenied() {
        throw new AppForbiddenException("User is not authorized");
    }

}
