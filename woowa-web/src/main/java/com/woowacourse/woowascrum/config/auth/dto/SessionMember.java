package com.woowacourse.woowascrum.config.auth.dto;

import com.woowacourse.woowascrum.member.domain.model.Member;
import lombok.Getter;

import java.io.Serializable;

/**
 * 인증된 사용자 정보만 필요로 한다.
 */
@Getter
public class SessionMember implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionMember(final Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
    }
}
