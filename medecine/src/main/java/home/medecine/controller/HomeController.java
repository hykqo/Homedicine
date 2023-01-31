package home.medecine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping({"/","/medicine"})
    public ModelAndView home(ModelAndView mav){
        mav.setViewName("/medicine/home");
        return mav;
    }
}
