package home.medecine.controller;

import home.medecine.dto.MemberDTO;
import home.medecine.entity.member.Member;
import home.medecine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @GetMapping("/find")
    public ModelAndView findUserPage(ModelAndView mav){
        mav.setViewName("/user/find");
        return mav;
    }

    @PostMapping("/join/id")
    public ResponseEntity idCheck(@RequestParam String id) throws Exception {
        memberService.checkId(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/join")
    @Transactional
    public ResponseEntity join(@Valid @RequestBody MemberDTO.Join join) throws Exception {
        memberService.Join(join);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity getMember(@PathVariable long idx){
        MemberDTO.MemberInfo memberInfo = memberService.findById(idx);
        String role = memberInfo.getGrade().toString();
        System.out.println(role);
        return new ResponseEntity(memberInfo, HttpStatus.OK);
    }


    @PostMapping("/find/id")
    public ResponseEntity findId(@RequestBody @Valid MemberDTO.FindIdDTO findIdInfo){
        return new ResponseEntity(memberService.findIdByName$Phone(findIdInfo), HttpStatus.OK);
    }

    @PostMapping("/find/pw")
    public ResponseEntity findPw(@RequestBody @Valid MemberDTO.FindPwDTO findPwInfo){
        memberService.findById$Name$Phone(findPwInfo);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/update/pw")
    @Transactional
    public ResponseEntity updatePw(@RequestBody @Valid MemberDTO.FindPwDTO findPwDTO){
        Member member = memberService.findById$Name$Phone(findPwDTO);
        memberService.updatePassword(member, findPwDTO.getPw());
        return new ResponseEntity(HttpStatus.OK);
    }
}
