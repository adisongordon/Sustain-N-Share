package eco.sustainnshare.webapp.signup;

import eco.sustainnshare.webapp.dto.UserDto;
import eco.sustainnshare.webapp.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final UsersService userService;

    @PostMapping("/sign-up")
    public String signUp(Model model, UserDto userDto) {
        try {
            var user = userService.createUser(userDto);
            return "signup-success";
        } catch(RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "signup-failure";
        }
    }

    @GetMapping("/sign-in")
    public String signInOrUp(Model model){
        model.addAttribute("user", new UserDto());
        return "sign-in-or-up";
    }
}
