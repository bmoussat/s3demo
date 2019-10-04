package com.storage.s3demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.storage.s3demo.s3.S3ObjectRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileUploadController {

	@Autowired
	private S3ObjectRepository s3ObjectRepository;

	@Value("${localaws.s3-bucket.name}")
	private String bucketName;

	@GetMapping("/bucket/home")
	public String homePage(Model model) {
		retrieveAllFiles(model);
		return "home";
	}

	@RequestMapping(value = "/bucket/upload", method = RequestMethod.POST)
	public String uploadFile(Model model, @RequestParam("file") MultipartFile file) throws IOException {

		log.info("uploading the file : " + file.getName());
		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String fileType = file.getContentType();
			InputStream is = file.getInputStream();
			s3ObjectRepository.save(bucketName, fileName, is.readAllBytes(), fileType);
		}

		return "redirect:/bucket/home";
	}

	private void retrieveAllFiles(Model model) {
		List<String> files = s3ObjectRepository.getFilesNames(bucketName);
		model.addAttribute("files", files);
	}

}
