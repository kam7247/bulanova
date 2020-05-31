package ru.egprojects.sw1_springboot.model;

public class Pages {
    public static final String HOME = "/";
    public static final String SIGN_UP = "/signUp";
    public static final String SIGN_IN = "/signIn";
    public static final String USERS = "/users";
    public static final String PROFILE = "/profile";
    public static final String EDIT_PROFILE = PROFILE + "/edit";
    public static final String POST = "/post";
    public static final String CREATE_POST = PROFILE + POST + "/create";
    public static final String EDIT_POST = POST + "/edit";
    public static final String SEARCH = "/search";
    private static final String CONFIRMATION = "/confirm";
    public static final String EMAIL_CONFIRMATION = CONFIRMATION + "/email";
    public static final String PHONE_CONFIRMATION = CONFIRMATION + "/phone";
}
