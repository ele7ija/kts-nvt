package ftn.ktsnvt.culturalofferings.dto;

import ftn.ktsnvt.culturalofferings.model.UserRole;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    @NotBlank(message = "User first name cannot be blank!")
    private String firstName;

    @NotBlank(message = "User last name cannot be blank!")
    private String lastName;

    @NotBlank(message = "User email cannot be blank!")
    private String email;

    @NotBlank(message = "User password cannot be blank!")
    private String password;

    @NotBlank(message = "User role cannot be blank!")
    private UserRole userRole;

    private boolean enabled;

    private List<Long> newsIds;

    private List<Long> subscriptionIds;

    private List<Long> commentIds;

    private List<Long> ratingIds;

    public UserDTO(){}

    public UserDTO(String firstName, String lastName, String email, String password, UserRole userRole, boolean enabled, List<Long> newsIds, List<Long> subscriptionIds, List<Long> commentIds, List<Long> ratingIds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.enabled = enabled;
        this.newsIds = newsIds == null ? new ArrayList<>() : newsIds;
        this.subscriptionIds = subscriptionIds == null ? new ArrayList<>() : subscriptionIds;
        this.commentIds = commentIds == null ? new ArrayList<>() : commentIds;
        this.ratingIds = ratingIds == null ? new ArrayList<>() : ratingIds;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Long> getNewsIds() {
        return newsIds;
    }

    public void setNewsIds(List<Long> newsIds) {
        this.newsIds = newsIds;
    }

    public List<Long> getSubscriptionIds() {
        return subscriptionIds;
    }

    public void setSubscriptionIds(List<Long> subscriptionIds) {
        this.subscriptionIds = subscriptionIds;
    }

    public List<Long> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<Long> commentIds) {
        this.commentIds = commentIds;
    }

    public List<Long> getRatingIds() {
        return ratingIds;
    }

    public void setRatingIds(List<Long> ratingIds) {
        this.ratingIds = ratingIds;
    }
}
