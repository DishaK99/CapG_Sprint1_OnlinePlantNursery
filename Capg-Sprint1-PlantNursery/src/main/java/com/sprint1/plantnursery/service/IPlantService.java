package com.sprint1.plantnursery.service;

import java.util.List;

import com.sprint1.plantnursery.entity.Plant;

/*Controller Class for Customer Controller
Author : Disha Kale
*/

public interface IPlantService {
	
	Plant addNewPlant(Plant plant);
	
	Plant updatePlant(Plant plant, int id);
	
	Plant deletePlant(int plantId);
	
	Plant getPlant(int plantId);
	
	Plant getPlant(String commonName);
	
	List<Plant> getAllPlants();
	
	List<Plant> getAllPlants(String typeOfPlant);

}
