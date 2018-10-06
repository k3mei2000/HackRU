package hackRU;
import java.util.*;



public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String address = scan.nextLine();
		String format = address;
		
		String houseNumber = null;
		String streetName = null;
		String cityName = null;
		String state = null;
		String zipCode = null;
		
		//The String Format is now all uppercase with no punctuation.
		format = format.toUpperCase();
		format = format.replaceAll("[^a-zA-Z0-9 ]", "");
		
		
		String[] tokens = format.split(" ");
		
		for (int i = 0; i < tokens.length; i++) {
			if (isNumeric(tokens[i]) && houseNumber == null) {
				houseNumber = tokens[i];
			} else if (isNumeric(tokens[i])) {
				zipCode = tokens[i];
			}
			
		}
		
		
		System.out.println(format);
		
		System.out.println("----------");
		System.out.println("House Number: " + houseNumber);
		System.out.println("Street Name : " + streetName);
		System.out.println("City Name   : " + cityName);
		System.out.println("State       : " + state);
		System.out.println("Zip Code    : " + zipCode);
		String[] arguments = {houseNumber, streetName, cityName, state, zipCode};
		String result = "";
		
		for (int i = 0; i < arguments.length; i++) {
			if (arguments[i] != null) {
				result = result + " " + toProperCase(arguments[i]);
			}
		}
		result = result.substring(1, result.length());
		
		System.out.println("----------");
		System.out.println(result);
	
	}
	
	public static boolean isNumeric(String s) {
		try {
			double d = Double.parseDouble(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static boolean isStreetEnding(String s) {
		String[] endings = {"STREET","ST","LANE","LN","CIRCLE","CIR",
				"DRIVE","DR","AVENUE","AVE","PARKWAY","PKWY","BOULEVARD",
				"BLVD","COURT","CT","TERRACE","HILL","PLACE","PL","SQUARE",
				"SQ","LOOP","ALLEY","ALY"
				
		};
		return true;
	}
	
	public static String toProperCase(String s) {
		String proper = s.substring(0,1) + s.substring(1,s.length()).toLowerCase();
		return proper;
	
		
	}
	
}
