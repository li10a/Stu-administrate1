package com.stu.administrate.controller.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.stu.administrate.controller.teacher.TeacherClassController;
import com.stu.administrate.model.User;
import com.stu.administrate.repository.HomeworkCommitHistoryRepository;
import com.stu.administrate.service.ClassService;
import com.stu.administrate.service.HomeworkService;

@Controller
@RequestMapping("/student")
public class StudentController {

	private Logger logger = LoggerFactory.getLogger(TeacherClassController.class);

	@Autowired
	private HomeworkService homeworkService;

	@Autowired
	private HomeworkCommitHistoryRepository homeworkCommitHistoryRepository;

	@Autowired
	private ClassService classService;

	@Autowired
	@Qualifier("propertyConfigurer")
	private PropertiesFactoryBean propertyConfigurer;

	@GetMapping("/overview")
	public String overview(Model model, @RequestAttribute("loginUser") User loginUser) {
		model.addAttribute("classInfo", classService.selectClassByNo(loginUser.getClassNo()));
		model.addAttribute("homeworkList", homeworkService.selectAllHomeworkByClassNoDesc(loginUser.getClassNo()));
		model.addAttribute("homeworkCommitHistoryList", homeworkCommitHistoryRepository.selectAllHomeworkCommitHistory());
		return "/student/overview";
	}

	@PostMapping("/uploadHomework")
	public String uploadHomework(Model model, @RequestParam("homeworkNo") int homeworkNo, @RequestParam("file") MultipartFile file) {
		return "";
	}
}
