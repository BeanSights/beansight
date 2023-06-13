package com.ll.beansight.boundedContext.member.service;

import com.ll.beansight.base.rsData.RsData;
import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.member.repository.MemberRepository;
import com.ll.beansight.boundedContext.tag.entity.MemberTag;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import com.ll.beansight.boundedContext.tag.repository.MemberTagRepository;
import com.ll.beansight.boundedContext.tag.repository.TagRepository;
import com.ll.beansight.boundedContext.tag.service.MemberTagService;
import com.ll.beansight.boundedContext.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 아래 메서드들이 전부 readonly 라는 것을 명시, 나중을 위해
public class MemberService {
    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;
    private final TagService tagService;
    private final MemberTagRepository memberTagRepository;

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional
    public RsData<Member> join(String username, String nickname, String password) {

        return join("BEANSIGHT", username, nickname, password);
    }

    // 내부 처리함수, 일반회원가입, 소셜로그인을 통한 회원가입(최초 로그인 시 한번만 발생)에서 이 함수를 사용함
    private RsData<Member> join(String providerTypeCode, String username, String nickname, String password) {
        if (findByUsername(username).isPresent()) {
            return RsData.of("F-1", "해당 아이디(%s)는 이미 사용중입니다.".formatted(username));
        }

        // 소셜 로그인을 통한 회원가입에서는 비번이 없다.
        if (StringUtils.hasText(password)) password = passwordEncoder.encode(password);

        Member member = Member
                .builder()
                .providerTypeCode(providerTypeCode)
                .username(username)
                .nickname(nickname)
                .password(password)
                .build();

        memberRepository.save(member);

        return RsData.of("S-1", "회원가입이 완료되었습니다.", member);
    }



    // 소셜 로그인(카카오, 구글, 네이버) 로그인이 될 때 마다 실행되는 함수
    @Transactional
    public RsData<Member> whenSocialLogin(String providerTypeCode, String username, String nickname) {
        Optional<Member> opMember = findByUsername(username); // username 예시 : KAKAO__1312319038130912, NAVER__1230812300

        if (opMember.isPresent()) return RsData.of("S-2", "로그인 되었습니다.", opMember.get());

        // 소셜 로그인를 통한 가입시 비번은 없다.
        return join(providerTypeCode, username, nickname, ""); // 최초 로그인 시 딱 한번 실행
    }

    @Transactional
    public RsData<String> updateMemberTagList(Member member, List<String> tagList) {
        List<MemberTag> memberTags = new ArrayList<>();
        for (String tagId : tagList) {

            Optional<Tag> opTag = tagService.getTag(Long.parseLong(tagId));
            if (!opTag.isPresent()) return RsData.of("F-1", "실패");
            MemberTag memberTag = MemberTag
                    .builder()
                    .member(member)
                    .tag(opTag.get())
                    .build();
            memberTagRepository.save(memberTag);
            member.getTagList().add(memberTag);
        }


        return RsData.of("S-1", "성공");
    }

    public Optional<Member> findByMemberId(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
