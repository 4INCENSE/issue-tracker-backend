package com.incense.issuetracker.domain.user;

public enum AuthenticationType {
    LOCAL, GOOGLE, NAVER, KAKAO;

    public static AuthenticationType getType(String uri) {
        if(uri.contains("google")) {
            return GOOGLE;
        }

        if(uri.contains("naver")) {
            return NAVER;
        }

        if(uri.contains("kakao")) {
            return KAKAO;
        }

        return LOCAL;
    }
}
