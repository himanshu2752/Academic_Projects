package copart_WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author himanshu
 *
 */
@Path("/Rest_CrudOperations")
public class restWebService {
	

		public static HashSet<Integer> vehicleIds = new HashSet<>();
		public static HashMap<Integer, vehicle_Class> vehicles = new HashMap<>();	
		
		
		@GET 
		@Produces(MediaType.TEXT_XML)	
		@Path("/vehicleInfo/id={id}")
		public static String getVehicleInformation(@PathParam("id") int id)
		{
			String response = "" ;
			if (vehicleIds.contains(id))
			{
				vehicle_Class v = vehicles.get(id);
				 response ="<?xml version='1.0'?>"+ "<VehicleInfo>"+"<vehicleID>"+id+"</vehicleID>"+
						"<vehicleClass>"+v.getVehicleClass() +"</vehicleClass>" + "<vehicleMake>"+v.getVehicleMake()+"</vehicleMake>"+
						"<vehicleModel>"+v.getVehicleModel()+"</vehicleModel>" +
						"<vehicleYear>"+v.getVehicleYear()+"</vehicleYear>"+"</VehicleInfo>" ;
				return response;
			}
			else{
				response = "<?xml version='1.0'?>"+"<vehicleID>No information</vehicleID>";
				return response;
			}
			
		}
		
		@GET
		@Produces(MediaType.TEXT_HTML)
		@Path("/Access/{param}={value}")
		public static String accessVehicleInfo(@PathParam("param") String par, @PathParam("value") String val)
		{
		
			ArrayList<vehicle_Class> matchedVehicles = new ArrayList<>();
			String response = "";
			
			if (par.equals("class")){
			for (Entry<Integer, vehicle_Class> v : vehicles.entrySet())
			{		
				if (v.getValue().Class.equals(val))
				{
					matchedVehicles.add(v.getValue());
				}
			}
			}
			else if (par.equals("make"))
			{
				for (Entry<Integer, vehicle_Class> v : vehicles.entrySet())
				{			
					if (v.getValue().Make.equals(val))
					{			
						matchedVehicles.add(v.getValue());
					}
				}
			}
			else if (par.equals("model"))
			{
				for (Entry<Integer, vehicle_Class> v : vehicles.entrySet())
				{			
					if (v.getValue().Model.equals(val))
					{			
						matchedVehicles.add(v.getValue());
					}
				}
			}
			else if (par.equals("year"))
			{
				int year = Integer.parseInt(val);
				for (Entry<Integer, vehicle_Class> v : vehicles.entrySet())
				{				
				
					if (v.getValue().Year == year)
					{
						matchedVehicles.add(v.getValue());
					}
				}
			}
			if (!matchedVehicles.isEmpty()){
			response = "<html> <head> <title>Vehicle Information</title> </head> <body> <UL>";
			for (vehicle_Class v : matchedVehicles)
			{
				response = response + "<LI>" + "VehicleID = "+ v.getVehicleId() + ", class = "+ v.getVehicleClass()+
						", make = "+ v.getVehicleMake()+
						", model = "+ v.getVehicleModel()+
						" and year = "+ v.getVehicleYear() ;
			}
			response = response + "</UL> </body> </html>";		
			}
			else
			{
				response = "<h1>no record found for this vehicle</h1>";
			}
			return response;
		}
		
		/** 
		 * @param Throwing information for invalid scenarios/Automated Test cases
		 * 
		 */	
		@GET
		@Produces(MediaType.TEXT_HTML)
		@Path("/create/class={class}")
		public static String invalidCreateVehicle1(@PathParam("class") String clas){
			String response = "<h1>Please enter make, model and year also</h1>";
			return response;
		}
		
		@GET
		@Produces(MediaType.TEXT_HTML)
		@Path("/create/class={class}/model={model}")
		public static String invalidCreateVehicle2(@PathParam("class") String clas,@PathParam("model") String model){
			String response = "<h1>Please enter make and year also</h1>";
			return response;
		}
		
		@GET
		@Produces(MediaType.TEXT_HTML)
		@Path("/create/class={class}/model={model}/make={make}")
		public static String invalidCreateVehicle3(@PathParam("class") String clas,@PathParam("model") String model,
				@PathParam("make") String make){
			String response = "<h1>Please enter 'year' also</h1>";
			return response;
		}
		
		
		@GET
		@Produces(MediaType.TEXT_HTML)
		@Path("/create/class=/model={model}/make={make}/year={year}")
			public static String invalidCreateVehicle4(){
			String response = "<h1>Class cannot be null</h1>";
			return response;
		}
		
		@GET
		@Produces(MediaType.TEXT_HTML)
		@Path("/create/class={class}/model=/make={make}/year={year}")
			public static String invalidCreateVehicle5(){
			String response = "<h1>Model cannot be null</h1>";
			return response;
		}
		
		@GET
		@Produces(MediaType.TEXT_HTML)
		@Path("/create/class={class}/model={model}/make=/year={year}")
			public static String invalidCreateVehicle6(){
			String response = "<h1>Make cannot be null</h1>";
			return response;
		}
		
		@GET
		@Produces(MediaType.TEXT_HTML)
		@Path("/create/class={class}/model={model}/make={make}/year=")
			public static String invalidCreateVehicle7(){
			String response = "<h1>year cannot be null</h1>";
			return response;
		}
		
		// More test cases can be added later for other invalid scenarios.//
		
		/**
		 * 
		 * Valid create operation
		 */
		@GET
		@Produces(MediaType.TEXT_HTML)
		@Path("/create/class={class}/model={model}/make={make}/year={year}")
		public static String createVehicle(@PathParam("class") String clas,
				@PathParam("model") String model,
				@PathParam("make") String make,
				@PathParam("year") int year
				){
			if (make == null || clas == null || model == null || year < 1950 || year > 2050 )
			{
				String makeResponse = "";
				String modelResponse = "";
				String clasResponse = "";
				String yearResponse = "";
				String response = "";
				if (make == "")
				{
					makeResponse = "can not create the vehicle, Please enter make of the vehicle. ";
				}
				if (clas == "")
				{
					clasResponse = "can not create the vehicle, Please enter class of the vehicle. ";
				}
				if (model == "")
				{
					modelResponse = "can not create the vehicle, Please enter model of the vehicle. ";
				}
				if (year < 1950 || year > 2050)
				{
					yearResponse = "can not create the vehicle, Please enter year between 1950 and 2050. ";
				}
				
				response = "<html> <head> <title>Plz correct the following things</title> </head> <body> <UL><LI>"+
						makeResponse + 
					"<LI>" +modelResponse+ 
					"<LI>" + clasResponse+
					"<LI>" +yearResponse+
				"</UL> </body> </html>";
				return response;
			}
			else{
				vehicle_Class v = new vehicle_Class();
			int id = vehicles.size()+1 ;
			v.setId(id);
			v.setClass(clas);
			v.setMake(make);
			v.setModel(model);
			v.setYear(year);
			vehicles.put(id, v);
			vehicleIds.add(v.Id);
			String x = "<h1>Vehicle created successfully</h1>";
			return x;}
			
		}

		/** 
		 * below method can be implemented if we want to store the created objects in a binary file and retrive it later
		 */
		/*public static void writeTofile(Vehicle v) throws IOException{
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Vehicle.bin"));
			
			objectOutputStream.writeObject(v);
		}
		public static void readFile(){
			
		}*/
		
		/**
		 * 
		 * Valid Update Operation
		 */
		@GET
		@Produces(MediaType.TEXT_HTML)
		@Path("/update/vehicleID={id}/class={class}/make={make}/model={model}/year={year}")
		public static String updateVehicle(@PathParam("id") int id,
				@PathParam("class") String clas,
				@PathParam("make") String make,
				@PathParam("model") String model,
				@PathParam("year") int year) {
			String response = "" ;
			if (vehicleIds.contains(id))
			{
				vehicle_Class v = vehicles.get(id);
				v.setClass(clas);
				v.setMake(make);
				v.setModel(model);
				v.setYear(year);
				response = "<h1>Vehicle id= "+ id+ "updated successfully</h1>";
				return response;
			}
			else {
				response = "<h1> no such vehicle in the system </h1>";
				return response;
			}		
		}
		
		/**
		 * Valid Delete Operation
		 * @param Vehicleid
		 * 
		 */
		@GET
		@Produces(MediaType.TEXT_HTML)
		@Path("/delete/vehicleID={id}")
		public static String deleteVehicle(@PathParam("id") int id){
			String response = "" ;
			if (vehicleIds.contains(id))
			{
				vehicle_Class v = vehicles.remove(id);			
				response = "<h1>Vehicle id= "+ id+ "removed  successfully</h1>";
				return response;
			}
			else {
				response = "<h1> no such vehicle in the system </h1>";
				return response;
			}
		}

}
