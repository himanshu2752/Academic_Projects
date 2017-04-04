package copart_WebService;

import java.awt.image.DataBufferByte;
import java.io.Serializable;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "vehicle" , schema="class_vehicle")
/**
 * @author himanshu
 * @class Vehicle 
 *
 */
public class vehicle_Class implements Serializable {
	
//	BufferedImage hugeImage = ImageIO.read(PerformanceTest.class.getResource("12000X12000.jpg"));
	
	@Id
	@Column(name = "vehicleID" , nullable = false)
	public int Id ;
	
	@Column(name="vehicle_year")
	public int Year;
	
	@Column(name="vehicle_Make")
	public String Make;
	
	@Column(name="vehicle_Model")
	public String Model;
	
	@Column(name="vehicle_Class")
	public String Class ;
	
	@Column(name="auction_date")
	public String auction_date ;
	
	@Column(name="auction_yard")
	public String auction_yard ;
	
	@Column(name="address")
	public String address ;
	
	@Column(name="damage_description")
	public String damage_description ;
	
//	@Column(name="auction_yard")
//	public byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();

	@Override
	public String toString() {
		return "Vehicle [Id=" + Id + ", Year=" + Year + ", Make=" + Make + ", Model=" + Model + ", Class=" + Class
				+  ", auction_date="+ auction_date + ", auction_yard=" + auction_yard + ", address=" + address + ", damage=" + damage_description +"]";
	}


	/**
	 * 
	 * Constructor for vehicle
	 */
	/*	public Vehicle(int id, int year, String make, String model, String clas){
		this.Id = id;
		this.Year = year;
		this.Make = make;
		this.Model = model;
		this.Class = clas;
	}*/
	public int getVehicleId(){
		return Id ;
	}
	
	
	public int getVehicleYear(){
		return Year;
	}
	
	public String getVehicleMake(){
		return Make;
	}
	
	public String getVehicleModel(){
		return Model;
	}
	
	public String getVehicleClass(){
		return Class;
	}
	
	public void setId(int id)
	{
		this.Id = id ;
	}
	
	public void setYear(int year)
	{
		this.Year = year;
	}
	
	public void setMake(String make)
	{
		this.Make = make;
	}
	
	public void setModel(String model)
	{
		this.Model = model ;
	}
	
	public void setClass(String clas)
	{
		this.Class = clas ;
	}
	
	public String getAuctionDate(){
		return auction_date ;
	}
	
	public String getAuctionYard(){
		return auction_yard ;
	}
	
	public String getaddress(){
		return address ;
	}
	
	public String getdamageDescription(){
		return damage_description ;
	}
	
}
