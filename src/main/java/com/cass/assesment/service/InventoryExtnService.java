package com.cass.assesment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.stereotype.Service;

import com.cass.assesment.Exception.DuplicateItemIdException;
import com.cass.assesment.Exception.InventoryNotfoundException;
import com.cass.assesment.model.InventoryExtn;


@Service
public class InventoryExtnService {
	
	@Autowired
	private InventoryExtnRepository repository;
	
	public InventoryExtn addInventoryExtn(InventoryExtn invExtn) {
		String itemId = invExtn.getItemId();
		/*
		 * if(repository.existsById(itemId)) { throw new DuplicateItemIdException(); }
		 */
		if(repository.existsById(itemId)){
			throw new DuplicateItemIdException();
		}
		repository.save(invExtn);
		return invExtn;
	}
	
	public InventoryExtn getInvtoryExtnByItemId(String itemId) {
		 
		if(!repository.existsById(itemId)){
			throw new InventoryNotfoundException();
		}
		return repository.findByItemId(itemId);
	}
	
	public void deleteInvtoryExtnByItemId(String itemId) {
		repository.deleteById(itemId);
		
	}
	
	public InventoryExtn updateInventoryExtn(InventoryExtn invExtn) {
		String itemId = invExtn.getItemId();
		if(!repository.existsById(itemId)) {
			throw  new InventoryNotfoundException();
		}
		return repository.save(invExtn);
	}
	
	public Boolean inventoryExtnExist(String itemId) {
		if(repository.existsById(itemId)) {
			return true;
		}
		return false;
	}
	
	public InventoryExtn getAvailabilty(InventoryExtn invExtn) {
		String itemId = invExtn.getItemId();
		if(!repository.existsById(itemId)) {
			throw  new InventoryNotfoundException();
		}
		InventoryExtn inv = repository.findById(itemId).get();
		Double supply = inv.getSupply();
		Double demand = inv.getDemand();
		Double atp = supply - demand;
		
		Double eFactor = inv.getEligibilityFactor();
		Double promisable = eFactor * atp;
		
		inv.setAtp(atp);
		inv.setPromisable(promisable);
		
		return inv;
	}
	

}
