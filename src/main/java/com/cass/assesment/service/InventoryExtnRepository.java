package com.cass.assesment.service;

import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.repository.CrudRepository;

import com.cass.assesment.model.InventoryExtn;


public interface InventoryExtnRepository extends CrudRepository<InventoryExtn, String> {
	public InventoryExtn findByItemId(String id);

}
