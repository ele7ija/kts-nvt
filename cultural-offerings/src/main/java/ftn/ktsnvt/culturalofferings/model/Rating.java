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

    @Column
    @Temporal(TemporalType.TIME)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private CulturalOffering culturalOffering;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Rating() {}

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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