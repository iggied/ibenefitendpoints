package com.iggie.ibenefitendpoints;

import com.google.api.server.spi.config.ApiResourceProperty;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class Outlet implements Serializable {


  private static final long serialVersionUID = 1L;

  public Outlet() {};

  public Outlet(int merchantId, int outletId, String name, String placeId) {
	this.merchantId = merchantId;
	this.outletId = outletId;
    this.name = name;
    this.placeId = placeId;
  }

  
  @Attribute(primaryKey = true)
  private Key id;

  @Attribute(version = true)
  private Long version;

  /**
   * Returns the key.
   *
   * @return the key
   */
  public Key getId() {
      return id;
  }

  /**
   * Sets the key.
   *
   * @param key
   *            the key
   */
  public void setId(Key id) {
      this.id = id;
  }

  /**
   * Returns the version.
   *
   * @return the version
   */
  public Long getVersion() {
      return version;
  }

  /**
   * Sets the version.
   *
   * @param version
   *            the version
   */
  public void setVersion(Long version) {
      this.version = version;
  }
  
  
  
  
  public int merchantId ;
	
  public int getMerchantId() {
	return merchantId;
  }
  public void setMerchantId(int merchantId) {
    this.merchantId = merchantId;
  }

  public int outletId ;
  public int getOutletId() {
	return outletId;
  }

  public void setOutletId(int outletId) {
	this.outletId = outletId;
  }
  
  public String name;
  public String getName() {
	  return name;
  }
  public void setName( String name) {
	  this.name = name;
  }

  @ApiResourceProperty
  private String placeId;
  public String getPlaceId() {
	  return placeId;
  }
  public void setPlaceId(String placeId) {
	  this.placeId = placeId;
  }
  
  @Attribute(listener = CreationDate.class)
  public Date createdAt;

public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}
}

