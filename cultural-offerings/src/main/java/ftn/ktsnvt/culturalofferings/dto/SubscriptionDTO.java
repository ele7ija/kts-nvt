package ftn.ktsnvt.culturalofferings.dto;

public class SubscriptionDTO {
    
    private Long culturalOffering;
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
}
