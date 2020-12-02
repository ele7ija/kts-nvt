package ftn.ktsnvt.culturalofferings.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.ktsnvt.culturalofferings.model.Image;
import ftn.ktsnvt.culturalofferings.repository.ImageRepository;

@Service
public class ImageService implements ServiceInterface<Image> {
	
	@Autowired
	private ImageRepository imageRepository;

	@Override
	public List<Image> findAll() {
		// this method should be optional or replaced with findAll(Pageable pageable)
		return null;
	}

	@Override
	public Image findOne(Long id) {
		return imageRepository.findById(id).orElse(null);
	}
	
	public Image findByName(String name) {
		return imageRepository.findByName(name).orElse(null);
	}

	@Override
	public Image create(Image entity) throws Exception {
		return imageRepository.save(entity);	
	}

	@Override
	public Image update(Image entity, Long id) throws Exception {
		Image existingImage = imageRepository.findById(id).orElse(null);
        if(existingImage == null){
            throw new Exception("Image with given id doesn't exist");
        }
        return imageRepository.save(existingImage);
	}

	@Override
	public void delete(Long id) throws Exception {
		Image existingImage = imageRepository.findById(id).orElse(null);
        if(existingImage == null){
            throw new Exception("Image with given id doesn't exist");
        }
        imageRepository.delete(existingImage);
	}
	
	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		
		try {
			outputStream.close();
		} catch (IOException e) {}
		
		return outputStream.toByteArray();	
    }

	// uncompress the image bytes before returning it (to the angular application)
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		
		try {
			
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			
			outputStream.close();
			
		} catch (IOException ioe) {
		} catch (DataFormatException e) {}
		
		return outputStream.toByteArray();
	}



}
