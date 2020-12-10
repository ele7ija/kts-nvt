package ftn.ktsnvt.culturalofferings.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NewsDTO {
    @NotBlank(message = "News title cannot be empty")
    private String title;
    @NotBlank(message = "News text cannot be empty")
    private String text;
    private Date date;
    private List<Long> images;
    @NotNull(message = "Cultural offering id must be provided")
    @Positive(message = "Cultural offering id must be a positive number")
    private Long culturalOffering;
    @NotNull(message = "User id must be provided")
    @Positive(message = "User id must be a positive number")
    private Long user;

    public NewsDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Long> getImages() {
        return images;
    }

    public void setImages(List<Long> images) {
        this.images = images;
    }

    public Long getCulturalOffering() {
        return culturalOffering;
    }

    public void setCulturalOffering(Long culturalOffering) {
        this.culturalOffering = culturalOffering;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
    
}
