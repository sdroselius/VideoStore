package com.skilldistillery.bootmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.bootmvc.data.FilmDAO;
import com.skilldistillery.jpavideostore.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = { "/", "index.do" })
	public String index(Model model) {
		model.addAttribute("filmList", filmDao.findAll());
		return "index";
	}

	@RequestMapping(path = "getFilm.do", method = RequestMethod.GET)
	public String displayFilm(Model model, @RequestParam Integer fid) {
		Film film = filmDao.findById(fid);
		model.addAttribute("film", film);
		return "film/show";
	}

}
