package ftn.ktsnvt.culturalofferings.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CulturalOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn
    private Location location;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "culturalOffering")
    private Set<Rating> ratings;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "culturalOffering")
    private Set<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "culturalOffering")
    private Set<Subscription> subscriptions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "culturalOffering")
    private Set<News> news;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private CulturalOfferingType culturalOfferingType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private CulturalOfferingSubType culturalOfferingSubType;

    public CulturalOffering() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    public CulturalOfferingType getCulturalOfferingType() {
        return culturalOfferingType;
    }

    public void setCulturalOfferingType(CulturalOfferingType culturalOfferingType) {
        this.culturalOfferingType = culturalOfferingType;
    }

    public CulturalOfferingSubType getCulturalOfferingSubType() {
        return culturalOfferingSubType;
    }

    public void setCulturalOfferingSubType(CulturalOfferingSubType culturalOfferingSubType) {
        this.culturalOfferingSubType = culturalOfferingSubType;
    }
}
