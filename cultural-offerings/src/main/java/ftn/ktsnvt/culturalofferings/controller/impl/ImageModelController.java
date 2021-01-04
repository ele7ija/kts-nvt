package ftn.ktsnvt.culturalofferings.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import ftn.ktsnvt.culturalofferings.controller.api.ImageModelApi;
import ftn.ktsnvt.culturalofferings.model.ImageModel;
import ftn.ktsnvt.culturalofferings.service.ImageService;

@Controller
public class ImageModelController implements ImageModelApi{

	@Autowired
	ImageService imageService;

	@Override
	public ResponseEntity<ImageModel> uploadImage(MultipartFile file) throws Exception {
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		ImageModel imageInDatabase = imageService.create(new ImageModel(file.getOriginalFilename(), file.getContentType(), imageService.compressBytes(file.getBytes())));
		ImageModel imageForFrontend = new ImageModel(imageInDatabase.getId(), imageInDatabase.getName(), imageInDatabase.getType(), imageService.decompressBytes(imageInDatabase.getPicByte()));
		return new ResponseEntity<>(imageForFrontend, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ImageModel> getImage(Long imageId) {
		ImageModel imageInDatabase = imageService.findById(imageId);
		ImageModel imageForFrontend = new ImageModel(imageId, imageInDatabase.getName(), imageInDatabase.getType(), imageService.decompressBytes(imageInDatabase.getPicByte()));
		return new ResponseEntity<>(imageForFrontend, HttpStatus.OK);

	}
	
}
