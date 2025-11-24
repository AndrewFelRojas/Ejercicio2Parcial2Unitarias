package Ejercicio;

public class InterestCalculator { 
	// Calcula interés compuesto anual 
	public double calculateCompound(double principal, double rate, int 
	years){ 
	        if (principal < 0) throw new IllegalArgumentException("Principal must be positive"); 
	        if (rate < 0) throw new IllegalArgumentException("Rate must be positive"); 
	        if (years < 0) throw new IllegalArgumentException("Years must be positive"); 
	 
	        return principal * Math.pow(1 + rate, years); 
	    } 
	 
	    // Calcula el pago mensual de un préstamo 
	    public double monthlyPayment(double principal, double rate, int 
	months){ 
	        if (principal <= 0 || rate < 0 || months <= 0) 
	            throw new IllegalArgumentException("Invalid values"); 
	 
	        double monthlyRate = rate / 12; 
	        return (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months)); 
	    } 
	}