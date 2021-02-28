package glfp.glfp.controller;

import glfp.glfp.domain.entity.Member;
import glfp.glfp.dto.MemberDto;
import glfp.glfp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{member_id}") //조회
    public ResponseEntity<MemberDto> getMember(@PathVariable("member_id") Long mId){
        MemberDto memberDTO = memberService.getMember(mId);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    @PostMapping("") //등록 ->Post 방식이기 때문에 RequestBody를 통해 HTTP요청 body를 자바 객체로 받을 수 있다.
    public ResponseEntity<String> register(@RequestBody MemberDto memberDto){
        MemberDto newMemberDto = MemberDto
                .builder()
                .id(memberDto.getId())
                .userAccount(memberDto.getUserAccount())
                .userPasswd(passwordEncoder.encode(memberDto.getUserPasswd()))
                .userName(memberDto.getUserName())
                .userSex(memberDto.getUserSex())
                .userEmail(memberDto.getUserEmail())
                .role(memberDto.getRole()).build();

        memberService.join(newMemberDto);

        return new ResponseEntity<>(memberDto.getUserName(), HttpStatus.OK);
    }

    @PutMapping("") //수정
    public ResponseEntity<String> revise(@RequestBody MemberDto memberDto){
        memberService.revise(memberDto);
        return new ResponseEntity<>(memberDto.getUserName(), HttpStatus.OK);
    }

    @DeleteMapping("/{member_id}") //삭제
    public ResponseEntity<String> delete(@PathVariable("member_id") Long mId){
        String userName = memberService.getMember(mId).getUserName();
        memberService.delete(mId);
        return new ResponseEntity<>(userName, HttpStatus.OK);
    }


}
