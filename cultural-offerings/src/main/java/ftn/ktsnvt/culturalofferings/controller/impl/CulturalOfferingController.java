package ftn.ktsnvt.culturalofferings.controller.impl;

import ftn.ktsnvt.culturalofferings.controller.api.CulturalOfferingApi;
import ftn.ktsnvt.culturalofferings.model.CulturalOffering;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class CulturalOfferingController implements CulturalOfferingApi {

    private static final Logger log = LoggerFactory.getLogger(CulturalOfferingController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CulturalOfferingController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CulturalOffering> createCulturalOffering(@RequestBody CulturalOffering body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CulturalOffering>(objectMapper.readValue("{  \"images\" : [ \"images\", \"images\" ],  \"newsletters\" : [ {    \"user\" : {      \"firstName\" : \"firstName\",      \"lastName\" : \"lastName\",      \"password\" : \"password\",      \"subscriptions\" : [ {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      }, {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      } ],      \"newsletters\" : [ null, null ],      \"username\" : \"username\"    }  }, {    \"user\" : {      \"firstName\" : \"firstName\",      \"lastName\" : \"lastName\",      \"password\" : \"password\",      \"subscriptions\" : [ {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      }, {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      } ],      \"newsletters\" : [ null, null ],      \"username\" : \"username\"    }  } ],  \"subscriptions\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\"  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\"  } ],  \"comments\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"images\" : [ \"images\", \"images\" ],    \"text\" : \"text\"  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"images\" : [ \"images\", \"images\" ],    \"text\" : \"text\"  } ],  \"subtype\" : {    \"name\" : \"name\"  },  \"ratings\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"value\" : 1  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"value\" : 1  } ],  \"name\" : \"name\",  \"description\" : \"description\",  \"location\" : {    \"latitude\" : 6.02745618307040320615897144307382404804229736328125,    \"name\" : \"name\",    \"longitude\" : 0.80082819046101150206595775671303272247314453125  },  \"type\" : {    \"name\" : \"name\",    \"icon\" : \"icon\"  }}", CulturalOffering.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CulturalOffering>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CulturalOffering>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteCulturalOffering(@PathVariable("id") String id) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CulturalOffering> getCulturalOfferingByID(@PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CulturalOffering>(objectMapper.readValue("{  \"images\" : [ \"images\", \"images\" ],  \"newsletters\" : [ {    \"user\" : {      \"firstName\" : \"firstName\",      \"lastName\" : \"lastName\",      \"password\" : \"password\",      \"subscriptions\" : [ {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      }, {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      } ],      \"newsletters\" : [ null, null ],      \"username\" : \"username\"    }  }, {    \"user\" : {      \"firstName\" : \"firstName\",      \"lastName\" : \"lastName\",      \"password\" : \"password\",      \"subscriptions\" : [ {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      }, {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      } ],      \"newsletters\" : [ null, null ],      \"username\" : \"username\"    }  } ],  \"subscriptions\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\"  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\"  } ],  \"comments\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"images\" : [ \"images\", \"images\" ],    \"text\" : \"text\"  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"images\" : [ \"images\", \"images\" ],    \"text\" : \"text\"  } ],  \"subtype\" : {    \"name\" : \"name\"  },  \"ratings\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"value\" : 1  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"value\" : 1  } ],  \"name\" : \"name\",  \"description\" : \"description\",  \"location\" : {    \"latitude\" : 6.02745618307040320615897144307382404804229736328125,    \"name\" : \"name\",    \"longitude\" : 0.80082819046101150206595775671303272247314453125  },  \"type\" : {    \"name\" : \"name\",    \"icon\" : \"icon\"  }}", CulturalOffering.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CulturalOffering>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (accept != null && accept.contains("application/xml")) {
            try {
                return new ResponseEntity<CulturalOffering>(objectMapper.readValue("<null>  <name>aeiou</name>  <images>aeiou</images>  <description>aeiou</description></null>", CulturalOffering.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/xml", e);
                return new ResponseEntity<CulturalOffering>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CulturalOffering>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CulturalOffering> updateCulturalOffering(@RequestBody CulturalOffering body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CulturalOffering>(objectMapper.readValue("{  \"images\" : [ \"images\", \"images\" ],  \"newsletters\" : [ {    \"user\" : {      \"firstName\" : \"firstName\",      \"lastName\" : \"lastName\",      \"password\" : \"password\",      \"subscriptions\" : [ {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      }, {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      } ],      \"newsletters\" : [ null, null ],      \"username\" : \"username\"    }  }, {    \"user\" : {      \"firstName\" : \"firstName\",      \"lastName\" : \"lastName\",      \"password\" : \"password\",      \"subscriptions\" : [ {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      }, {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      } ],      \"newsletters\" : [ null, null ],      \"username\" : \"username\"    }  } ],  \"subscriptions\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\"  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\"  } ],  \"comments\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"images\" : [ \"images\", \"images\" ],    \"text\" : \"text\"  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"images\" : [ \"images\", \"images\" ],    \"text\" : \"text\"  } ],  \"subtype\" : {    \"name\" : \"name\"  },  \"ratings\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"value\" : 1  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"value\" : 1  } ],  \"name\" : \"name\",  \"description\" : \"description\",  \"location\" : {    \"latitude\" : 6.02745618307040320615897144307382404804229736328125,    \"name\" : \"name\",    \"longitude\" : 0.80082819046101150206595775671303272247314453125  },  \"type\" : {    \"name\" : \"name\",    \"icon\" : \"icon\"  }}", CulturalOffering.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CulturalOffering>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CulturalOffering>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CulturalOffering> uploadImageCulturalOffering(@PathVariable("id") String id, @RequestPart(value="file", required=true) MultipartFile file) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CulturalOffering>(objectMapper.readValue("{  \"images\" : [ \"images\", \"images\" ],  \"newsletters\" : [ {    \"user\" : {      \"firstName\" : \"firstName\",      \"lastName\" : \"lastName\",      \"password\" : \"password\",      \"subscriptions\" : [ {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      }, {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      } ],      \"newsletters\" : [ null, null ],      \"username\" : \"username\"    }  }, {    \"user\" : {      \"firstName\" : \"firstName\",      \"lastName\" : \"lastName\",      \"password\" : \"password\",      \"subscriptions\" : [ {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      }, {        \"date\" : \"2000-01-23T04:56:07.000+00:00\"      } ],      \"newsletters\" : [ null, null ],      \"username\" : \"username\"    }  } ],  \"subscriptions\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\"  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\"  } ],  \"comments\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"images\" : [ \"images\", \"images\" ],    \"text\" : \"text\"  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"images\" : [ \"images\", \"images\" ],    \"text\" : \"text\"  } ],  \"subtype\" : {    \"name\" : \"name\"  },  \"ratings\" : [ {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"value\" : 1  }, {    \"date\" : \"2000-01-23T04:56:07.000+00:00\",    \"value\" : 1  } ],  \"name\" : \"name\",  \"description\" : \"description\",  \"location\" : {    \"latitude\" : 6.02745618307040320615897144307382404804229736328125,    \"name\" : \"name\",    \"longitude\" : 0.80082819046101150206595775671303272247314453125  },  \"type\" : {    \"name\" : \"name\",    \"icon\" : \"icon\"  }}", CulturalOffering.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CulturalOffering>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CulturalOffering>(HttpStatus.NOT_IMPLEMENTED);
    }

}
