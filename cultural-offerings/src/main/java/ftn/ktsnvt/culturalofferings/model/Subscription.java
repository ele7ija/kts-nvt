package ftn.ktsnvt.culturalofferings.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private CulturalOffering culturalOffering;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Subscription() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subscription s = (Subscription) o;
        return Objects.equals(id, s.getId());
    }
}
