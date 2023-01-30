package home.medecine.controller;

import home.medecine.dto.MemberDTO;
import home.medecine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/login")
    public ModelAndView loginPage(ModelAndView mav){
        mav.setViewName("/user/login");
        return mav;
    }

    @GetMapping("/join")
    public ModelAndView joinPage(ModelAndView mav){
        mav.setViewName("/user/join");
        return mav;
    }

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody MemberDTO.Join join) throws Exception {
        memberService.Join(join);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/join/id")
    public ResponseEntity idCheck(@RequestParam String id) throws Exception {
        memberService.checkId(id);
        return new ResponseEntity(HttpStatus.OK);
    }



}