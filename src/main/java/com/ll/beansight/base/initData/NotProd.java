package com.ll.beansight.base.initData;


import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.member.service.MemberService;
import com.ll.beansight.boundedContext.tag.service.TagService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
    @Bean
    CommandLineRunner initData(
            MemberService memberService,
            TagService tagService
    ) {
        return new CommandLineRunner() {
            @Override
            @Transactional
            public void run(String... args) throws Exception {
                tagService.setTag(100L, "대화하기 좋은");
                tagService.setTag(101L, "깔끔한");
                tagService.setTag(102L, "인스타감성");
                tagService.setTag(103L, "스터디");
                tagService.setTag(200L, "주차장");
                tagService.setTag(201L, "콘센트");
                tagService.setTag(202L, "넓은 매장");
                tagService.setTag(203L, "깨끗한 화장실");
                tagService.setTag(300L, "착한 가격");
                tagService.setTag(301L, "디저트");
                tagService.setTag(400L, "쿠폰적립");
                tagService.setTag(401L, "친절한 매장");
            }
        };
    }
}
