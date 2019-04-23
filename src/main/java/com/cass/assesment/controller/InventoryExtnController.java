package com.cass.assesment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cass.assesment.Exception.DuplicateItemIdException;
import com.cass.assesment.Exception.InventoryNotfoundException;
import com.cass.assesment.model.InventoryExtn;
import com.cass.assesment.service.InventoryExtnService;


@RestController
public class InventoryExtnController {
	
	@Autowired
	private InventoryExtnService service;
	
	@RequestMapping(value="/hello")
	public String hello() {
		return "hi";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/addinv")
	public InventoryExtn createInventoryExtn(@RequestBody InventoryExtn invExtn) 
			throws DuplicateItemIdException{
		return service.addInventoryExtn(invExtn);
		
	}
	
	@RequestMapping("/getinv/{itemId}")
	public InventoryExtn getInventoryExtnByItemId(@PathVariable String itemId) 
			throws InventoryNotfoundException {
		return service.getInvtoryExtnByItemId(itemId);
	}
	
	@RequestMapping("/deleteinv/{itemId}")
	public ResponseEntity<Object> deleteInventoryExtnByItemId(@PathVariable String itemId)
			throws InventoryNotfoundException {
		
		service.deleteInvtoryExtnByItemId(itemId);
		if(!service.inventoryExtnExist(itemId)) {
			return new ResponseEntity<>("Inventory is deleted successfully", HttpStatus.OK);
		}
		return null;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/updateinv")
	public InventoryExtn updateInventoryExtn(@RequestBody InventoryExtn invExtn)
			throws InventoryNotfoundException {
		return service.updateInventoryExtn(invExtn);
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST,value="/getavailability")
	public InventoryExtn getAvailability(@RequestBody InventoryExtn invExtn) 
			throws InventoryNotfoundException {
		return service.getAvailabilty(invExtn);
	}
	
}
