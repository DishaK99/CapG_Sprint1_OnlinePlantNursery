package com.sprint1.plantnursery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.plantnursery.service.IPlantService;

import com.sprint1.plantnursery.entity.Plant;
import com.sprint1.plantnursery.exceptions.PlantIdNotFoundException;

/*Controller Class for Customer Controller
Author : Disha Kale
*/

@RestController
@RequestMapping("/plants")
public class IPlantController {
	
	@Autowired
	private IPlantService plantService;
	
	/****************************
	 * Method: addPlant 
	 * Description: It is used to add into the plant table 
	 * @returns plant It returns String type message
	 * @PostMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * 
	 ****************************/
	
	@PostMapping("/addPlant")
	public ResponseEntity<Plant> addNewPlant(@RequestBody Plant plant) {
		plantService.addNewPlant(plant);
		return new ResponseEntity<Plant>(plant, HttpStatus.CREATED);
	}
// ========================================================================================================================================	
	
	/****************************
	 * Method: Update plant 
	 * Description: It is used to update plant in plant table
	 * @returns plant It returns String type message 
	 * @PutMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 * 
	 ****************************/
	@PutMapping({"/updatePlant/id/{id}"})
	public ResponseEntity<Plant> updatePlant(@RequestBody Plant plant, @PathVariable int id) {
		// logger.trace("updating the whole plant having id "+ id);  
		plantService.updatePlant(plant, id);
		return new ResponseEntity<Plant>(plant, HttpStatus.ACCEPTED);
	}
// ========================================================================================================================================	

	
	/****************************
	 * Method: deletePlant
	 * Description: It is used to remove the items in the plant table
	 * @returns plant It returns String type message 
	 * @DeleteMapping: It is used to handle the HTTP Delete requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 *
	 ****************************/
	
	@DeleteMapping("/deletePlant/id/{id}")
	public ResponseEntity<Plant> deletePlant(@PathVariable int id) throws PlantIdNotFoundException{
		//logger.trace("deleting the whole plant");	
		return new ResponseEntity<Plant>(plantService.deletePlant(id), HttpStatus.OK);
	}

// ========================================================================================================================================	
	
	/****************************
	 * Method: viewAllPlants
	 * Description: It is used to view all order items in plant table
	 * @returns plant It returns plant with details
	 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 ****************************/
	
	@GetMapping("/viewAllPlants")
	public ResponseEntity<List<Plant>> viewAllPlants() {
		//logger.trace("fetching all the plants");
		return new ResponseEntity<List<Plant>>(plantService.getAllPlants(), HttpStatus.OK);
	}

// ========================================================================================================================================	
	/****************************
	 * Method: viewPlant by Id
	 * Description: It is used to view all order items in plant table
	 * @returns plant It returns plant with details
	 * @GetMapping: It is used to handle the HTTP POST requests matched with given URI expression.
	 * @RequestBody: It used to bind the HTTP request/response body with a domain object in method parameter or return type.
	 ****************************/
	
	@GetMapping("/viewPlant/id/{id}")
	public ResponseEntity<Plant> viewPlant(@PathVariable Integer id) {
		//logger.trace("fetching the plant with id "+ id);
		return new ResponseEntity<Plant>(plantService.getPlant(id), HttpStatus.OK);
	}

	

//	@GetMapping("/ViewAllPlants/typeOfPlant/{typeOfPlant}")
//	public ResponseEntity<List<Plant>> viewAllPlants(@PathVariable String typeOfPlant) {
//		//logger.trace("searching the plants of type "+typeOfPlant);
//		return new ResponseEntity<List<Plant>>(plantService.getAllPlants(typeOfPlant), HttpStatus.OK);
//	}
	
}
