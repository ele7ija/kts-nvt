package ftn.ktsnvt.culturalofferings.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {
    public static ResponseEntity ok(){
        return new ResponseEntity(HttpStatus.OK);
    }

    public static ResponseEntity ok(Object object){
        return new ResponseEntity(object, HttpStatus.OK);
    }

    public static ResponseEntity created(Object object){
        return new ResponseEntity(object, HttpStatus.CREATED);
    }
}
