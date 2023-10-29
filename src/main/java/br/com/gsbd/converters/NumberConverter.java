package br.com.gsbd.converters;

public class NumberConverter {

	
	public static boolean isNumeric(String strNum) {
		if(strNum == null) return false;
		
		String regex = "[-+]?\\d*\\.?\\d+";
		String number = strNum.replaceAll(",", ".");
	    return strNum.matches(regex);
	}
	
	
	public static double convertToDouble(String strNum) {
		if(strNum == null) return 0D;
		
		String number = strNum.replaceAll(",", ".");
		
		if(isNumeric(number)) {
			return Double.parseDouble(number);
		}
		
		return 0D;
	}
	
}
