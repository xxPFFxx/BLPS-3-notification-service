package com.example.uploadingfiles;

import java.io.IOException;
import java.util.stream.Collectors;

import com.example.uploadingfiles.services.UserService;
import com.example.uploadingfiles.storage.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.uploadingfiles.storage.StorageFileNotFoundException;
import com.example.uploadingfiles.storage.StorageService;

@Controller
public class FileUploadController {

	private final StorageService storageService;
	private final UserService userService;

	@Autowired
	public FileUploadController(StorageService storageService, UserService userService) {
		this.storageService = storageService;
		this.userService = userService;
	}
	@Value("errortext") String errorText;

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));

		//return "infoAboutVideo";
		return "uploadForm";
	}

	@GetMapping("/infoAboutVideo")
	public String showInfoAboutVideo(Model model) throws IOException {

		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));

		//return "infoAboutVideo";
		return "infoAboutVideo";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	@PostMapping("/uploadVideo")
	public String handleFileUpload(@RequestParam String login, @RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (userService.checkUser(login)){
			System.out.println("Залогинен");
			storageService.store(file);
			//TODO проверить формат и длину видео
			//return "redirect:/infoAboutVideo";
			return "redirect:/";
		}
		else {
			System.out.println("Не залогинен");
			redirectAttributes.addFlashAttribute("message",
					"Пользователя с ником " + login + " не существует. Проверьте правильность ввода или зарегистрируйтесь.");
			return "redirect:/";
		}


	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public String handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return "uploadForm";
	}

	@ExceptionHandler(StorageException.class)
	public String handleStorageException(StorageException storageException, Model model){
		model.addAttribute("error", storageException.getMessage());
		return "uploadForm";
	}

}
