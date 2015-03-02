package com.iggie.ibenefitendpoints;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.slim3.datastore.Datastore;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.DefaultValue;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.BadRequestException;
import com.google.appengine.api.datastore.Key;
import com.firebase.client.Firebase;
/**
 * Defines v1 of a Outlets API, which provides methods for getting and setting outlets.
 */
@Api(
    name = "outlet",
    version = "v1",
    scopes = {Constants.EMAIL_SCOPE},
    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
    audiences = {Constants.ANDROID_AUDIENCE}
)
public class OutletApi {

  public List<Outlet> getOutlet(@Named("id") Integer id, @Named("key") @DefaultValue String apiKey) throws NotFoundException {
    try {
    	
    	OutletMeta o = OutletMeta.get();

    	List<Outlet> outlets = Datastore.query(o)
    			.filter(o.outletId.equal(id))
    			.asList();
    	
    	if (outlets.isEmpty()) {
    		throw new NotFoundException("Outlet not found with Id: " + id);
    	}else {
    		return outlets;
    	}
    	
    } catch (NullPointerException e) {
      throw new NotFoundException("getOutlet threw NullPointerException for Id: " + id);
    }
  }

  public List<Outlet> listOutlet(@Named("key") @DefaultValue String apiKey) throws BadRequestException {
	if (apiKey.isEmpty()) {
		throw new BadRequestException("Do not perform such action");
	}
    return Datastore.query(Outlet.class).asList();
  }

  public Outlet saveOutlet(@Named("merchantId") int merchantId, 
		  					@Named("outletId") int outletId, 
		  					@Named("name") String name, 
		  					@Named("placeId") String placeId,
		  					@Named("id") String id) 
  {
    Outlet outlet = new Outlet(merchantId, outletId, name, placeId);

    outlet.setId(Datastore.createKey(Outlet.class, id));

    Datastore.put(outlet);
    return outlet;
  }

  public Outlet saveOutletToFirebase(@Named("merchantId") int merchantId, 
			@Named("outletId") int outletId, 
			@Named("name") String name, 
			@Named("placeId") String placeId,
			@Named("id") String id) 
	{
		Outlet outlet = new Outlet(merchantId, outletId, name, placeId);
		
		Firebase outletRef = new Firebase("https://boiling-fire-4418.firebaseio.com/outlet");
		outletRef.setValue(outlet);
		return outlet;
	}
  
}
