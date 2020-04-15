package com.woowacourse.woowascrum.member.domain.model;

import com.woowacourse.woowascrum.generic.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    private String email;
    private String password;
    private String name;
    private String picture;
    @Enumerated(EnumType.STRING)
    private SocialType socialType;
    private Role role;
    private LocalDateTime updatedTime;

    @Builder
    public Member(final String email, final String password, final String name, final String picture, final SocialType socialType, final Role role, final LocalDateTime createdTime, final LocalDateTime updatedTime) {
        super(createdTime);
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = picture;
        this.socialType = socialType;
        this.role = role;
        this.updatedTime = updatedTime;
    }

    public Member update(final String name, final String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
