package ftn.ktsnvt.culturalofferings.dto;

import java.util.Date;
import java.util.List;

public class CommentDTO {

    private String text;

    private Date date;
    private List<Long> images;
    private Long culturalOffering;
    private Long user;

    public CommentDTO() {}

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
