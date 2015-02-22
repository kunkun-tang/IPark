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
package com.seyren.core.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BigDecimalDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.seyren.core.util.math.BigDecimalSerializer;

/**
 * This class represents a graphite target that needs to be monitored.
 * 
 * It stores current subscriptions
 * 
 * @author mark
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingLot {
    
    private String id;
    private String name;
    private double price;
    private double coorx;
    private double coory;
    private int max;
    private int available;
    private DateTime lastCheck;
    private boolean reserved=false;


    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public ParkingLot withId(String id) {
        setId(id);
        return this;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public ParkingLot withName(String name) {
        setName(name);
        return this;
    }

    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    public ParkingLot withPrice(double price) {
        setPrice(price);
        return this;
    }

    public double getCoorx() {
        return coorx;
    }
    
    public void setCoorx(double coorx) {
        this.coorx = coorx;
    }

    public ParkingLot withCoorx(double coorx) {
        setCoorx(coorx);
        return this;
    }

    public double getCoory() {
        return coory;
    }
    
    public void setCoory(double coory) {
        this.coory = coory;
    }

    public ParkingLot withCoory(double coory) {
        setCoory(coory);
        return this;
    }

    public int getMax() {
        return max;
    }
    
    public void setMax(int max) {
        this.max = max;
    }

    public ParkingLot withMax(int max) {
        setMax(max);
        return this;
    }

    public int getAvailable() {
        return available;
    }
    
    public void setAvailable(int available) {
        this.available = available;
    }

    public ParkingLot withAvailable(int available) {
        setAvailable(available);
        return this;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(DateTime lastCheck) {
        this.lastCheck = lastCheck;
    }
    
    public ParkingLot withLastCheck(DateTime lastCheck) {
        setLastCheck(lastCheck);
        return this;
    }

    public boolean getReserved() {
        return reserved;
    }
    
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public ParkingLot withReserved(boolean reserved) {
        setReserved(reserved);
        return this;
    }

}
