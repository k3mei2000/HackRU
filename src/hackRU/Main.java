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
		
		int houseNumberIndex = -1;
		int streetNameIndex = -1;
		int stateIndex = -1;
		
		String[] twoWordBeginnings = {"NEW","NORTH","RHODE","SOUTH","WEST"};
		
		
		//The String Format is now all uppercase with no punctuation.
		format = format.toUpperCase();
		format = format.replaceAll("[^a-zA-Z0-9 ]", "");
		
		
		String[] tokens = format.split(" ");
		
		for (int i = 0; i < tokens.length; i++) {
			//Check for HouseNumber and ZipCode
			if (isNumeric(tokens[i]) && houseNumber == null) {
				houseNumber = tokens[i];
				houseNumberIndex = i;
			} else if (isNumeric(tokens[i])) {
				zipCode = tokens[i];
			}
			
			
			//Check for StreetName
			if (houseNumberIndex != -1 && isStreetEnding(tokens[i]) ) {
				streetName = "";
				streetNameIndex = i;
				String streetEnding = abbreviateStreetEnding(tokens[i]);
				for (int j = houseNumberIndex + 1; j < i; j++) {
					streetName = streetName + " " + tokens[j];
				}
				streetName = streetName + " " + streetEnding;
				streetName = streetName.substring(1);
				
			}
			
			//Check for State
			if (streetNameIndex != -1 && isState(tokens[i])) {
				state = "";
				stateIndex = i;
				boolean isTwoWords = false;
				
				for (int j = 0; j < twoWordBeginnings.length; j++) {
					if(tokens[i].equals(twoWordBeginnings[j])) {
						isTwoWords = true;
						break;
					}
				}
				
				if (isTwoWords) { 
					state = tokens[i] + " " + tokens[i+1];
				} else {
					state = tokens[i];
				}
				
				state = abbreviateState(state);
			}
			
			if (stateIndex != -1) {
				cityName = "";
				for (int j = streetNameIndex + 1; j < stateIndex; j++) {
					cityName = cityName + " " + tokens[j];
				}
				cityName = cityName.substring(1);
			}
		}
		
		if (zipCode.length() == 9) {
			zipCode = zipCode.substring(0,5) + "-" + zipCode.substring(5);
		}
		
		
		
		System.out.println(format);
		
		System.out.println("----------");
		System.out.println("House Number: " + houseNumber);
		System.out.println("Street Name : " + streetName);
		System.out.println("City Name   : " + cityName);
		System.out.println("State       : " + state);
		System.out.println("Zip Code    : " + zipCode);
		String[] arguments = {houseNumber, streetName, cityName};
		String result = "";
		
		for (int i = 0; i < arguments.length; i++) {
			if (arguments[i] != null) {
				result = result + " " + toProperCase(arguments[i]);
			}
		}
		result = result + " " + state;
		result = result + " " + zipCode;
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
		String[] endings = {"STREET","LANE","CIRCLE",
				"DRIVE","AVENUE","PARKWAY","BOULEVARD",
				"COURT","TERRACE","HILL","PLACE","SQUARE",
				"LOOP","ALLEY"	
		};
		String[] shortened = {"ST","LN","CIR",
				"DR","AVE","PKWY",
				"BLVD","CT","TERRACE","HILL","PL",
				"SQ","LOOP","ALY"
		};
		for(int i = 0; i < endings.length; i++) {
			if (endings[i].equals(s) || shortened[i].equals(s)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isState(String s) {
		String[] states = {"ALABAMA","ALASKA","ARIZONA","ARKANSAS",
				"CALIFORNIA","COLORADO","CONNECTICUT","DELAWARE",
				"FLORIDA","GEORGIA","HAWAII","IDAHO","ILLINOIS","INDIANA",
				"IOWA","KANSAS","KENTUCKY","LOUISIANA","MAINE","MARYLAND",
				"MASSACHUSETTS","MICHIGAN","MINNESOTA","MISSISSIPPI","MISSOURI",
				"MONTANA","NEBRASKA","NEVADA","NEW","NORTH","OHIO",
				"OKLAHOMA","OREGON","PENNSYLVANIA","RHODE","SOUTH",
				"TENNESSEE","TEXAS","UTAH","VERMONT","VIRGINIA",
				"WASHINGTON","WEST","WISCONSIN","WYOMING"
		};
		String[] abbrevs = {"AL","AK","AZ","AR","CA","CO","CT","DE",
				"FL","GA","HI","ID","IL","IN","IA","KS","KY","LA",
				"ME","MD","MA","MI","MN","MS","MO","MT","NE","NV",
				"NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA",
				"RI","SC","SD","TN","TX","UT","VT","VA","WA","WV",
				"WI","WY",	
		};
		
		for(int i = 0; i < abbrevs.length; i++) {
			if (abbrevs[i].equals(s)) {
				return true;
			}
		}
		
		for(int i = 0; i < states.length; i++) {
			if (states[i].equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	public static String abbreviateStreetEnding(String s) {
		String[] endings = {"STREET","LANE","CIRCLE",
				"DRIVE","AVENUE","PARKWAY","BOULEVARD",
				"COURT","TERRACE","HILL","PLACE","SQUARE",
				"LOOP","ALLEY"	
		};
		String[] shortened = {"ST","LN","CIR",
				"DR","AVE","PKWY",
				"BLVD","CT","TERRACE","HILL","PL",
				"SQ","LOOP","ALY"
		};
		
		for (int i = 0; i < endings.length; i++) {
			if (endings[i].equals(s)) {
				return shortened[i];
			}
		}
		return s;
	}
	
	public static String abbreviateState(String s) {
		String[] states = {"ALABAMA","ALASKA","ARIZONA","ARKANSAS",
				"CALIFORNIA","COLORADO","CONNECTICUT","DELAWARE",
				"FLORIDA","GEORGIA","HAWAII","IDAHO","ILLINOIS","INDIANA",
				"IOWA","KANSAS","KENTUCKY","LOUISIANA","MAINE","MARYLAND",
				"MASSACHUSETTS","MICHIGAN","MINNESOTA","MISSISSIPPI","MISSOURI",
				"MONTANA","NEBRASKA","NEVADA","NEW HAMPSHIRE","NEW JERSEY",
				"NEW MEXICO","NEW YORK","NORTH CAROLINA","NORTH DAKOTA","OHIO",
				"OKLAHOMA","OREGON","PENNSYLVANIA","RHODE ISLAND","SOUTH CAROLINA",
				"SOUTH DAKOTA","TENNESSEE","TEXAS","UTAH","VERMONT","VIRGINIA",
				"WASHINGTON","WEST VIRGINIA","WISCONSIN","WYOMING"
		};
		String[] abbrevs = {"AL","AK","AZ","AR","CA","CO","CT","DE",
				"FL","GA","HI","ID","IL","IN","IA","KS","KY","LA",
				"ME","MD","MA","MI","MN","MS","MO","MT","NE","NV",
				"NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA",
				"RI","SC","SD","TN","TX","UT","VT","VA","WA","WV",
				"WI","WY",	
		};
		
		for (int i = 0; i < states.length; i++) {
			if (states[i].equals(s)) {
				return abbrevs[i];
			}
		}
		
		return s;
	}
	
	public static String toProperCase(String s) {
		String[] words = s.split(" ");
		String proper = "";
		
		for (int i = 0; i < words.length; i++) {
			proper = proper + " " + words[i].substring(0,1) + words[i].substring(1,words[i].length()).toLowerCase();
		}
		proper = proper.substring(1);
		return proper;
	}
	
}
