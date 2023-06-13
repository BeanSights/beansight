package com.ll.beansight.boundedContext.member.entity;

import com.ll.beansight.base.baseEntity.BaseEntity;
import com.ll.beansight.boundedContext.review.entity.CafeReview;
import com.ll.beansight.boundedContext.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder// Member.builder().providerTypeCode(providerTypeCode) .. 이런식으로 쓸 수 있게 해주는
@NoArgsConstructor // @Builder 붙이면 이거 필수
@AllArgsConstructor // @Builder 붙이면 이거 필수
@EntityListeners(AuditingEntityListener.class) // @CreatedDate, @LastModifiedDate 작동하게 허용
@ToString // 디버그를 위한
@Entity // 아래 클래스는 member 테이블과 대응되고, 아래 클래스의 객체는 테이블의 row와 대응된다.
@Getter// 아래 필드에 대해서 전부다 게터를 만든다. private Long id; => public Long getId() { ... }
public class Member extends BaseEntity {
    private String providerTypeCode; // 일반회원인지, 카카오로 가입한 회원인지, 구글로 가입한 회원인지
    @Column(unique = true)
    private String username;
    private String nickname;
    private String password;
    //TODO: 나중에 mappedby 넣고, controller에서 wish 부분 고치기, Tag -> MemberTag
    @OneToMany
    private List<Tag> tagList;
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<CafeReview> cafeReviewList;
    @OneToMany(mappedBy = "member", cascade = {CascadeType.REMOVE})
    private List<MemberWishList> wishLists;

    // 이 함수 자체는 만들어야 한다. 스프링 시큐리티 규격
    public List<? extends GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        // 모든 멤버는 member 권한을 가진다.
        grantedAuthorities.add(new SimpleGrantedAuthority("member"));

        // username이 admin인 회원은 추가로 admin 권한도 가진다.
        if ("admin".equals(username)) {
            grantedAuthorities.add(new SimpleGrantedAuthority("admin"));
        }

        return grantedAuthorities;
    }

    public boolean hasTagList(){
        return tagList.size() != 0;
    }
}
