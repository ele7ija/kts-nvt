package ftn.ktsnvt.culturalofferings.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class SubscriptionDTO {
    private Long id;

    @NotNull(message = "Cultural offering id must be provided")
    @Positive(message = "Cultural offering id must be a positive number")
    private Long culturalOffering;
    @NotNull(message = "User id must be provided")
    @Positive(message = "User id must be a positive number")
    private Long user;

    public SubscriptionDTO() {}

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


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
