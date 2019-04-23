package com.cass.assesment.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(value = "inventoryextn")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryExtn {
	
	//@PrimaryKeyColumn(name="itemId",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
	@PrimaryKey
	private String itemId;
	
	//@PrimaryKeyColumn(name = "locationId",ordinal = 1,type = PrimaryKeyType.CLUSTERED)
	private String locationId;
	
	@Column(value = "supply")
	private Double supply;
	
	@Column(value = "demand")
	private Double demand;
	
	@Column(value = "eligibilityfactor")
	private Double eligibilityFactor;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	
	@Transient // atp will not be persisted in database
	private Double atp;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient
	private Double promisable;
	
	public InventoryExtn(String itemId, String locationId, Double supply, Double demand, Double eligibilityFactor){
		this.itemId = itemId;
		this.locationId = locationId;
		this.supply = supply;
		this.demand = demand;
		this.eligibilityFactor = eligibilityFactor;
	}
	

}
