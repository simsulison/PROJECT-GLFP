package glfp.glfp.service;

import glfp.glfp.domain.entity.Member;
import glfp.glfp.domain.repository.MemberRepository;
import glfp.glfp.dto.MemberDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberDto getMember(Long mId){
        Member member = memberRepository.findById(mId).get();
        return member.toDto(member);
    }
}
