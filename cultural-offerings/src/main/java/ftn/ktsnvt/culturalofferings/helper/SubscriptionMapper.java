package ftn.ktsnvt.culturalofferings.helper;

import java.beans.JavaBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftn.ktsnvt.culturalofferings.dto.SubscriptionDTO;
import ftn.ktsnvt.culturalofferings.model.Subscription;
import ftn.ktsnvt.culturalofferings.service.CulturalOfferingService;
import ftn.ktsnvt.culturalofferings.service.UserService;

@Component
public class SubscriptionMapper implements MapperInterface<Subscription, SubscriptionDTO> {
    @Autowired
    UserService userService;

    @Autowired
    CulturalOfferingService culturalOfferingService;

    @Override
    public Subscription toEntity(SubscriptionDTO dto) {
        Subscription s = new Subscription();
        s.setUser(userService.findOne(dto.getUser()));
        s.setCulturalOffering(culturalOfferingService.findOne(dto.getCulturalOffering()));
        return s;
    }

    @Override
    public SubscriptionDTO toDto(Subscription entity) {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setCulturalOffering(entity.getCulturalOffering().getId());
        dto.setUser(entity.getUser().getId());
        return dto;
    }
    
}
