/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.seyren.api.bean;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.seyren.api.jaxrs.ParkingLotsResource;
import com.seyren.core.domain.ParkingLot;
import com.seyren.core.domain.SeyrenResponse;
import com.seyren.core.store.ParkingLotsStore;

@Named
public class ParkingLotsBean implements ParkingLotsResource {
    
    private ParkingLotsStore parkingLotsStore;
    
    @Inject
    public ParkingLotsBean(ParkingLotsStore parkingLotsStore) {
        this.parkingLotsStore = parkingLotsStore;
    }
    
    @Override
    public Response getParkinglots(String username, double x, double y, double radius) {
        SeyrenResponse<ParkingLot> pls = parkingLotsStore.getParklots(username, x, y, radius);
        return Response.ok(pls).build();
    }

    @Override
    public Response reservePark(String username, int parkID) {
        ParkingLot stored = parkingLotsStore.reserveParklot(username, parkID);
        return Response.ok(stored).build();
    }


    @Override
    public Response createParkinglot(ParkingLot pl) {

        ParkingLot stored = parkingLotsStore.createParklot(pl);
        return Response.created(uri(stored.getId())).build();
    }
    
    // @Override
    // public Response updateParkinglot(String plID, ParkingLot pl) {
    //     ParkingLot stored = parkingLotsStore.getParklot(plID);
    //     if (stored == null) {
    //         return Response.status(Status.NOT_FOUND).build();
    //     }
    //     stored = parkingLotsStore.saveParklot(pl);
    //     return Response.ok(stored).build();
    // }
    
    @Override
    public Response getParkinglot(String plID) {
        ParkingLot pl = parkingLotsStore.getParklot(plID);
        if (pl == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(pl).build();
    }
    
    private URI uri(String plID) {
        try {
            return new URI("parkinglots/" + plID);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    
}
