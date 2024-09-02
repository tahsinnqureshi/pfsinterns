package com.cms.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cms.entity.AddContent;
import com.cms.entity.AppUser;
import com.cms.repository.ContentRepository;
import com.cms.repository.UserRepository;

import java.io.IOException;


@Service
public class ContentService {

	@Autowired
	private ContentRepository contentRepository;

	@Autowired
	private UserRepository userRepository; // Assuming you have a repository for AppUser

	public void saveContent(String pTitle, String pAuthor, String content, MultipartFile file) throws IOException {
	    // Find the AppUser by email
	    

	    // Create a new AddContent object
	    AddContent addContent = new AddContent();
	    addContent.setpTitle(pTitle);
	    addContent.setpAuthor(pAuthor); // Set the AppUser as the author
	    addContent.setContent(content);

	    // Handle the file upload
	    if (file != null && !file.isEmpty()) {
	        addContent.setImgName(file.getOriginalFilename());
	        addContent.setImgData(file.getBytes());  // Ensure this does not exceed column limits
	    }

	    // Save the content to the database
	    contentRepository.save(addContent);
	}

    
        

}
