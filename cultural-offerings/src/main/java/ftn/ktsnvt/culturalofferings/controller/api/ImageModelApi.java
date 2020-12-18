package ftn.ktsnvt.culturalofferings.controller.api;

import java.io.IOException;

import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ftn.ktsnvt.culturalofferings.model.ImageModel;

@RequestMapping(value = "/images")
public interface ImageModelApi {
	
	
	@RequestMapping(value="/upload-image",
			method = RequestMethod.POST)
	BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException;		
	
	@RequestMapping(value="/get/{imageName}",
			method = RequestMethod.GET)
	ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException;
}
