package ftn.ktsnvt.culturalofferings.controller.api;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ftn.ktsnvt.culturalofferings.model.ImageModel;

@RequestMapping(value = "/images")
public interface ImageModelApi {
	
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<ImageModel> uploadImage(@RequestParam("imageFile") MultipartFile file) throws Exception;
	
	@RequestMapping(value="/{imageId}",
			method = RequestMethod.GET)
	ResponseEntity<ImageModel> getImage(@PathVariable("imageId") Long imageId) throws IOException;
}
