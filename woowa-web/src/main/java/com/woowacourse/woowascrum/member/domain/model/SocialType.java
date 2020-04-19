package com.woowacourse.woowascrum.member.domain.model;

public enum SocialType {
    GOOGLE("google"),
    GITHUB("github");

    private final String name;

    SocialType(final String name) {
        this.name = name;
    }

    public boolean isEqual(String name) {
        return this.name.equals(name);
    }
}
