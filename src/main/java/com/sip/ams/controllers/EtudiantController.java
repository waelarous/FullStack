package com.sip.ams.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sip.ams.entities.Etudiant;

import java.util.*;
@RequestMapping("/etudiant")

@Controller
public class EtudiantController {
	List<Etudiant> etudiants = new ArrayList<>();
	
	{
		
		etudiants.add(new Etudiant(1, "amine", "amine@gmail.com"));
		etudiants.add(new Etudiant(2, "rami", "rami@gmail.com"));
		etudiants.add(new Etudiant(3, "patrick", "patrick@gmail.com"));
	}

	@RequestMapping("/home")
	public String message(Model model) {
		System.out.println("Bienvenue au BootCamp");
		String formation = "fullstack 100% Spring boot";
		String lieu = "Sesame";
		model.addAttribute("training", formation);
		model.addAttribute("location", lieu);
		return "info";
	}
/*
	@RequestMapping("/produits")
	// public String listProduits(Model model)
	public ModelAndView listProduits() {
		ModelAndView mv = new ModelAndView();
		// model.addAttribute("mesProduits", produits);
		mv.addObject("mesProduits", produits);
		mv.setViewName("products");
		return mv;
		// return "products";
	}*/

	@RequestMapping("/students")
	public ModelAndView listStudents() {
		ModelAndView mv = new ModelAndView();

		System.out.println(this.etudiants);
		mv.addObject("students", etudiants);
		mv.setViewName("listStudents");
		return mv;
	}
	// @GetMapping("/add")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addStudentForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addStudent");
		return mv;

	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addStudent(
			@RequestParam("id") int id, 
			@RequestParam("nomEtudiant") String nom,
			@RequestParam("email") String email) {
		
		Etudiant e = new Etudiant(id, nom, email);
		etudiants.add(e);
		return "redirect:students";

	}

	
	@GetMapping("/delete/{ide}")
	
	public String suppression(@PathVariable("ide")int id) // id=ide
	{
		System.out.println("id ="+id);
		//on va supprimer ici
		Etudiant e =null;
		e = recherche(etudiants,id);
		etudiants.remove(e);
		return "redirect:../students";
	}
	@GetMapping("/update/{ide}")
	public ModelAndView getUpdateForm(@PathVariable("ide")int id) // id = ide
	{
		System.out.println("id = "+id);
		// on va supprimer ici
		Etudiant e = null;
		e =  recherche(etudiants, id);
		//etudiants.remove(e);
		ModelAndView mv = new ModelAndView();
		mv.addObject("etudiant", e);
		mv.setViewName("updateStudent");
		return mv;
	}
	
	
	@PostMapping("/update")
	public String updateEtudiant(Etudiant etudiant) // id = ide
	{
		//System.out.println(etudiant);
		int index = rechercheIndex(etudiants, etudiant);
		etudiants.set(index, etudiant);
		return "redirect:students";
	}
	
	
	private Etudiant recherche(List<Etudiant>le, int index)
	{
		Etudiant temp=null;
		for(Etudiant e : le)
		{
			if(e.getId()==index)
			{
				temp = e;
				return e;
			}
		}
		return temp;
	}
	
	private int rechercheIndex(List<Etudiant> le, Etudiant e)
	{
		int compteur = -1;
		for(Etudiant temp : le)
		{
			compteur++;
			if(temp.getId()==e.getId())
			{
				return compteur;
			}
			
		}
		return compteur;
	}

	

}