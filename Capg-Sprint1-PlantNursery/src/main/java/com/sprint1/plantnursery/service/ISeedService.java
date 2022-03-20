package com.sprint1.plantnursery.service;

import com.sprint1.plantnursery.entity.Seed;
//import com.capgsprint.onlineplantnursery.exception.SeedIdNotFoundException;

import java.util.List;
import java.util.Map;

/*Controller Class for Seed Controller
Created By : Smita Pradhan 
*/

public interface ISeedService {

	Seed addSeed(Seed seed);
	Seed updateSeed(int id, Map<Object, Object> fields);
	Seed deleteSeedById(int id);
	Seed getSeed(int id);
	Seed getSeed(String name);
	List<Seed> getSeeds();
	List<Seed> getSeeds(String typeOfSeed);
	
}
