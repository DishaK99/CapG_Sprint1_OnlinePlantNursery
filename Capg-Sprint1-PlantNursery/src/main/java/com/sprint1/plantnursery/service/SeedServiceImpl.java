package com.sprint1.plantnursery.service;

import org.springframework.data.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import com.sprint1.plantnursery.entity.Seed;
import com.sprint1.plantnursery.exceptions.SeedIdNotFoundException;
import com.sprint1.plantnursery.repository.ISeedRepository;

/*Controller Class for Seed Controller
Created By : Smita Pradhan 
*/

@Service
@Transactional
public class SeedServiceImpl implements ISeedService{
	
	
	@Autowired
	private ISeedRepository seedRepo;

	@Override
	public Seed addSeed(Seed seed) 
	{
		return seedRepo.save(seed);
	}
	
	
	@Override
	public Seed updateSeed(int id, Map<Object, Object> fields) {
		Optional<Seed> seedOptional = seedRepo.findById(id);
		if(seedOptional.isPresent()) {
		
			Seed seed = seedRepo.findById(id).get();
			fields.forEach((k,v)->{
				Field field = ReflectionUtils.findRequiredField(Seed.class, (String)k);
				field.setAccessible(true);
				ReflectionUtils.setField(field, seed, v);	
			});
			return seedRepo.save(seed);
		}
		return seedOptional.orElseThrow(() -> new SeedIdNotFoundException("Plant Not Found"));

}

	
	@Override
	public Seed deleteSeedById(int id) throws SeedIdNotFoundException {
		Optional<Seed> seedOptional = seedRepo.findById(id);		
		if(seedOptional.isPresent()) {
			seedRepo.delete(seedOptional.get());
		}
		return seedOptional.orElseThrow(()->new SeedIdNotFoundException("Invalid seed id...Cannot delete"));	
	}
	
	@Override
	public Seed getSeed(int id) throws SeedIdNotFoundException{
		Optional<Seed> seedOptional = seedRepo.findById(id);
		return seedOptional.orElseThrow(() -> new SeedIdNotFoundException("Seed Not Found...Invalid ID"));
	}

	@Override
	public Seed getSeed(String name) throws SeedIdNotFoundException {
		Optional<Seed> seedOptional = seedRepo.findByName(name);
		return seedOptional.orElseThrow(() -> new SeedIdNotFoundException("Seed Not Found...Invalid Name"));
	}

	
	@Override
	public List<Seed> getSeeds() {
		return seedRepo.findAll();
	}

	
	@Override
	public List<Seed> getSeeds(String type) {
		return seedRepo.findByTypeOfSeed(type);
	}

}
