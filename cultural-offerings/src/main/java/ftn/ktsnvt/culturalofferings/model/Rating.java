package ftn.ktsnvt.culturalofferings.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int value;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private CulturalOffering culturalOffering;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Rating() {}

    public Rating(Long id, int value, Date date, CulturalOffering culturalOffering, User user) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.culturalOffering = culturalOffering;
        this.user = user;
    }

    public Rating(Long id, int value, CulturalOffering culturalOffering, User user) {
        this.id = id;
        this.value = value;
        this.culturalOffering = culturalOffering;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
