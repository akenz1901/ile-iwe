package com.ileiwe.data.security.config;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGen";
    public static final long EXPIRATION_TIME = 845_000_000;//10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
