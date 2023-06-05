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
//                tagService.setTag("100");
//                tagService.setTag("101");
//                tagService.setTag("102");
//                tagService.setTag("103");
//                tagService.setTag("200");
//                tagService.setTag("201");
//                tagService.setTag("202");
//                tagService.setTag("203");
//                tagService.setTag("300");
//                tagService.setTag("301");
//                tagService.setTag("400");
//                tagService.setTag("401");
//                tagService.setTag("tagUp");
//                tagService.setTag("tagDown");
            }
        };
    }
}
