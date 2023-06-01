package com.ll.beansight.base.initData;


import com.ll.beansight.boundedContext.member.entity.Member;
import com.ll.beansight.boundedContext.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
    @Bean
    CommandLineRunner initData(
            MemberService memberService

    ) {
        return args -> {

        };
    }
}
