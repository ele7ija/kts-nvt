package ftn.ktsnvt.culturalofferings.model;

import javax.persistence.*;

import java.util.HashSet;
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
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ImageModel> images;

    public CulturalOffering() {}
    
    public CulturalOffering(String name, String description, Location location, Set<Rating> ratings,
			Set<Comment> comments, Set<Subscription> subscriptions, Set<News> news, Set<ImageModel> images,
			CulturalOfferingType culturalOfferingType, CulturalOfferingSubType culturalOfferingSubType) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.ratings = ratings;
		this.comments = comments;
		this.subscriptions = subscriptions;
		this.news = news;
		this.culturalOfferingType = culturalOfferingType;
		this.culturalOfferingSubType = culturalOfferingSubType;
		this.images = images;
	}
    
    public CulturalOffering(String name, String description, Location location, Set<ImageModel> images,
			CulturalOfferingType culturalOfferingType, CulturalOfferingSubType culturalOfferingSubType) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.ratings = new HashSet<Rating>();
		this.comments = new HashSet<Comment>();
		this.subscriptions = new HashSet<Subscription>();
		this.news = new HashSet<News>();
		this.culturalOfferingType = culturalOfferingType;
		this.culturalOfferingSubType = culturalOfferingSubType;
		this.images = images;
	}

    public CulturalOffering(Long id, String name, String description, Location location, Set<Rating> ratings,
			Set<Comment> comments, Set<Subscription> subscriptions, Set<News> news, Set<ImageModel> images,
			CulturalOfferingType culturalOfferingType, CulturalOfferingSubType culturalOfferingSubType) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.location = location;
		this.ratings = ratings;
		this.comments = comments;
		this.subscriptions = subscriptions;
		this.news = news;
		this.culturalOfferingType = culturalOfferingType;
		this.culturalOfferingSubType = culturalOfferingSubType;
		this.images = images;
	}

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

	public Set<ImageModel> getImages() {
		return images;
	}

	public void setImages(Set<ImageModel> images) {
		this.images = images;
	}
    
    
}
