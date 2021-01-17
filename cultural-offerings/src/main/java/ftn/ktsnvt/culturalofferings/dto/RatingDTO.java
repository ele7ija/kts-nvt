package ftn.ktsnvt.culturalofferings.dto;


import javax.validation.constraints.Positive;
import java.util.Date;

public class RatingDTO {
    private Long id;

    @Positive(message = "Value must be greater than zero")
    private int value;

    private Date date;

    @Positive(message = "Id for cultural offering is not valid")
    private Long culturalOfferingId;

    private Long userId;

    public RatingDTO() {}

    public RatingDTO(Long id, int value, Date date, Long culturalOfferingId, Long userId) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.culturalOfferingId = culturalOfferingId;
        this.userId = userId;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
