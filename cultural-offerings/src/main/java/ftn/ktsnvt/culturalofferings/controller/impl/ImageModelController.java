package ftn.ktsnvt.culturalofferings.controller.impl;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import ftn.ktsnvt.culturalofferings.controller.api.ImageModelApi;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.repository.ImageRepository;
import ftn.ktsnvt.culturalofferings.service.ImageService;

@Controller
public class ImageModelController implements ImageModelApi{

	 private static final Logger log = LoggerFactory.getLogger(ImageModelController.class);

	 private final ObjectMapper objectMapper;

	 private final HttpServletRequest request;
	 
	 @org.springframework.beans.factory.annotation.Autowired
	    public ImageModelController(ObjectMapper objectMapper, HttpServletRequest request) {
	        this.objectMapper = objectMapper;
	        this.request = request;
	    }
	    
     @Autowired
     ImageService imageService;
     
     @Autowired
     ImageRepository imageRepository;

	@Override
	public BodyBuilder uplaodImage(MultipartFile file) throws IOException {
		/*
		if(file.getBytes().length > ) {
			return ResponseEntity.status(HttpStatus.REQUEST_ENTITY_TOO_LARGE);
		}
		*/
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
		imageService.compressBytes(file.getBytes()));
		imageRepository.save(img);
		return ResponseEntity.status(HttpStatus.OK);

	}

	@Override
	public ImageModel getImage(String imageName) throws IOException {
		final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
		ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
		imageService.decompressBytes(retrievedImage.get().getPicByte()));
		return img;
		
	}
	
}
