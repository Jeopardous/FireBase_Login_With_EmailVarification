package com.example.adarsh.lovelyface.Models;

public class UserAccountSettings {

    private String username;
    private Long post;
    private Long friends;
    private String profilephoto;

    public UserAccountSettings(String username, Long post, Long friends, String profilephoto) {
        this.username = username;
        this.post = post;
        this.friends = friends;
        this.profilephoto = profilephoto;
    }

    public UserAccountSettings() {

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPost() {
        return post;
    }

    public void setPost(Long post) {
        this.post = post;
    }

    public Long getFriends() {
        return friends;
    }

    public void setFriends(Long friends) {
        this.friends = friends;
    }

    public String getProfilephoto() {
        return profilephoto;
    }

    public void setProfilephoto(String profilephoto) {
        this.profilephoto = profilephoto;
    }

}
