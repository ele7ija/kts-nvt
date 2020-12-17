package ftn.ktsnvt.culturalofferings.dto;


import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class RatingDTO {
    private Long id;

    @PositiveOrZero
    private int value;

    @Positive
    private Long culturalOfferingId;

    private Long userId;

    public RatingDTO() {}

    public RatingDTO(Long id, int value, Long culturalOfferingId, Long userId) {
        this.id = id;
        this.value = value;
        this.culturalOfferingId = culturalOfferingId;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCulturalOfferingId() {
        return culturalOfferingId;
    }

    public void setCulturalOfferingId(Long culturalOfferingId) {
        this.culturalOfferingId = culturalOfferingId;
    }

}
