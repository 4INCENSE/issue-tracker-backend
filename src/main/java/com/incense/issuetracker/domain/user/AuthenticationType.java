package com.incense.issuetracker.domain.user;

public enum AuthenticationType {
    LOCAL, GOOGLE, NAVER, KAKAO;

    public static AuthenticationType getType(String snsType) {
        if(snsType.contains("google")) {
            return GOOGLE;
        }

        if(snsType.contains("naver")) {
            return NAVER;
        }

        if(snsType.contains("kakao")) {
            return KAKAO;
        }

        return LOCAL;
    }
}
