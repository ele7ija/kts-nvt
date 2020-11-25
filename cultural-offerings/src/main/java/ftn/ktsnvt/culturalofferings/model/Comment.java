package ftn.ktsnvt.culturalofferings.model;

import ftn.ktsnvt.culturalofferings.converter.StringListConverter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column
    @Temporal(TemporalType.TIME)
    private Date date;

    @Convert(converter = StringListConverter.class)
    @Column(length = 1024)
    private List<String> images;

    @ManyToOne(fetch = FetchType.EAGER)
    private CulturalOffering culturalOffering;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Comment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public CulturalOffering getCulturalOffering() {
        return culturalOffering;
    }

    public void setCulturalOffering(CulturalOffering culturalOffering) {
        this.culturalOffering = culturalOffering;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
