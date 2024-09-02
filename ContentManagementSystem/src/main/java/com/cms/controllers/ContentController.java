package com.cms.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Base64;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.cms.entity.AddContent;
import com.cms.entity.AppUser;
import com.cms.repository.ContentRepository;
import com.cms.repository.UserRepository;
import com.cms.utils.ImageUtils;


import jakarta.servlet.http.HttpSession;



@Controller
public class ContentController {

	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/newContent")
	public String uploadForm(HttpSession session, Model model) {
	    AppUser storedUser = (AppUser) session.getAttribute("user");

	    if (storedUser == null) {
	        return "redirect:/login"; // Redirect to login if user is not found in session
	    }

	    model.addAttribute("user", storedUser);
	    model.addAttribute("addContent", new AddContent());

	   
	    return "newContent";
	}
	
	@GetMapping("/view-post")
	public String viewPost(HttpSession session, Model model) {
	    AppUser storedUser = (AppUser) session.getAttribute("user");

	    if (storedUser == null) {
	        return "redirect:/admin-user"; // Redirect to login if user is not found in session
	    }
	    model.addAttribute("user", storedUser);
	    
	    List<AddContent> contentList = contentRepository.findAll();
	    
	    // Make sure `getImgData()` returns a byte array.
	  
	   List<String> imgDataList = contentList.stream()
               .map((AddContent content) -> {
                   byte[] imgData = content.getImgData();
                   return imgData != null ? ImageUtils.encodeImage(imgData) : null;
               })
               .collect(Collectors.toList());


	    model.addAttribute("contentList", contentList);
	    model.addAttribute("imgDataList", imgDataList);
	    
	    return "viewPost";
	}

	@GetMapping("/search")
	public String filter(@RequestParam("pId") Long pId, HttpSession session, Model model) {
	    AppUser storedUser = (AppUser) session.getAttribute("user");

	    if (storedUser == null) {
	  
	        return "redirect:/view-post"; // Redirect to login if user is not found in session
	    }
	    model.addAttribute("user", storedUser);
	    
	    List<AddContent> contentList = contentRepository.findBypId(pId);
        model.addAttribute("contentList", contentList);
	    
	    // Make sure `getImgData()` returns a byte array.
	  
	   List<String> imgDataList = contentList.stream()
               .map((AddContent content) -> {
                   byte[] imgData = content.getImgData();
                   return imgData != null ? ImageUtils.encodeImage(imgData) : null;
               })
               .collect(Collectors.toList());


	    model.addAttribute("contentList", contentList);
	    model.addAttribute("imgDataList", imgDataList);
	    
	    return "viewPost";
	}

	@PostMapping("/delete")
	public String deleteContent(@RequestParam("pId") Long pId, Model model) {
	    Optional<AddContent> byId = contentRepository.findById(pId);

	    if (byId.isPresent()) {
	        contentRepository.deleteById(pId);
	        model.addAttribute("msg", "Post deleted successfully.");
	        return "redirect:/view-post"; // Redirect to prevent form resubmission
	    }

	    model.addAttribute("msg", "Post not found.");
	    return "redirect:/view-post"; // Redirect even if post is not found
	}

	@GetMapping("/edit")
	public String editUser(@RequestParam(value = "pId", required = false) Long pId, HttpSession session, Model model) {
	    AppUser storedUser = (AppUser) session.getAttribute("user");

	    if (storedUser == null) {
	        return "redirect:/login"; // Redirect to login if user is not found in session
	    }

	    model.addAttribute("user", storedUser);

	    AddContent addContent = new AddContent(); // Default initialization

	    if (pId != null) {
	        Optional<AddContent> content = contentRepository.findById(pId);
	        if (content.isPresent()) {
	            addContent = content.get();
	        }
	    }

	    model.addAttribute("addContent", addContent); // Add the addContent object to the model

	    return "newContent";
	}


	
	    @PostMapping("/upload-Content")
	    public String uploadContent(@RequestParam(value = "pId", required = false) Long pId,
	                                @RequestParam("pTitle") String pTitle,
	                                @RequestParam("pAuthor") String pAuthor, // Use pAuthorEmail to get the user by email
	                                @RequestParam("content") String content,
	                                @RequestParam("file") MultipartFile file,
	                                Model model) {
	        try {
	            AddContent addContent;

	            if (pId != null) {
	                // If pId is present, fetch the existing post and update it
	                Optional<AddContent> optionalContent = contentRepository.findById(pId);
	                if (optionalContent.isPresent()) {
	                    addContent = optionalContent.get();
	                } else {
	                    model.addAttribute("dmessage", "Failed to find post with ID " + pId);
	                    return "redirect:/newContent?dmessage=Failed to find post with ID " + pId;
	                }
	            } else {
	                // If pId is not present, create a new post
	                addContent = new AddContent();
	            }

	            // Set the title and content
	            addContent.setpTitle(pTitle);
	            addContent.setContent(content);

	            // Set the author's email
	            addContent.setpAuthor(pAuthor);

	            // Handle the file upload
	            if (!file.isEmpty()) {
	                addContent.setImgName(file.getOriginalFilename());
	                addContent.setImgData(file.getBytes());  // Convert file to byte array
	            }

	            // Save the post object to the database
	            contentRepository.save(addContent);
	            model.addAttribute("message", "Post uploaded successfully!");

	        } catch (Exception e) {
	            model.addAttribute("dmessage", "Failed to upload post.");
	            e.printStackTrace();
	        }
	        return "redirect:/newContent?message=Post uploaded successfully!";
	    }

	 @GetMapping("/")
	    public String viewContent(Model model) {
		   List<AddContent> contentList = contentRepository.findAll();
		    
		    // Make sure `getImgData()` returns a byte array.
		  
		   List<String> imgDataList = contentList.stream()
                   .map((AddContent content) -> {
                       byte[] imgData = content.getImgData();
                       return imgData != null ? ImageUtils.encodeImage(imgData) : null;
                   })
                   .collect(Collectors.toList());


		    model.addAttribute("contentList", contentList);
		    model.addAttribute("imgDataList", imgDataList);
		    
		    return "viewContent";
	    }
	 
	 @GetMapping("/welcomePage")
	    public String welcomeUser(HttpSession session ,Model model) {
		 AppUser storedUser = (AppUser) session.getAttribute("user");

		    if (storedUser == null) {
		        return "redirect:/login"; // Redirect to login if user is not found in session
		    }
		    model.addAttribute("user", storedUser);
		 return"welcomepage";
	 }
	 
}
