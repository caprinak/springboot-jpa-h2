package com.caprinak.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.caprinak.demo.dao.AlienRepo;
import com.caprinak.demo.model.Alien;

//Using H2
//http://localhost:8080/h2-console


//@RestController
@Controller
public class AlienController {
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		repo.save(alien);
		return "home.jsp";
	}

	/*
	 * @RequestMapping("/getAlien") public ModelAndView getAlien(@RequestParam int
	 * aid) { ModelAndView mv = new ModelAndView("showalien.jsp"); Alien alien =
	 * repo.findById(aid).orElse(new Alien()); mv.addObject(alien); return mv; }
	 */
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("showalien.jsp");
		Alien alien =  repo.findById(aid).orElse(new Alien());
		
		System.out.println(repo.findByTech("java"));
		System.out.println(repo.findByAidGreaterThan(102));
		System.out.println(repo.findByTechSorted("java"));
		
		mv.addObject(alien);
		return mv;
	}
	//REST INTRO
	/*
	 * @RequestMapping("/aliens")
	 * 
	 * @ResponseBody public String getAliens() { return repo.findAll().toString(); }
	 * 
	 * @RequestMapping("/alien/{aid}")
	 * 
	 * @ResponseBody public String getAlienAsResource(@PathVariable("aid") int aid)
	 * { return repo.findById(aid).toString(); }
	 */
	
	//Using Postman
	@RequestMapping(path="/aliens",produces= {"application/xml"})
	@ResponseBody
	public List<Alien> getAliens() {
		return (List<Alien>) repo.findAll();
	}
	@RequestMapping("/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getAlienAsResource(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}
	//content negotiation JSON XML plaintext
	//Spring Boot by default support JSoN
	//show XML on postman add Jackson Dataformat XML 
	
	@PostMapping("/alien")
	public Alien aAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	//Produce vs consume
	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable int aid) {
		Optional<Alien> a = repo.findById(aid);
		repo.deleteById(aid);
		return "deleted";
	}	
	
	@PutMapping("/alien")	
	public String saveOrUpdateAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return "updated";
	}
	//Spring Data Rest
}