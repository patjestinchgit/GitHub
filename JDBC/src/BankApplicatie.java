//Patrick Steens

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankApplicatie {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		bankApplicatie();
	}

	private static void bankApplicatie() {
		// TODO Auto-generated method stub
		
		Uitvoering uitvoering =new Uitvoering();
		int keuzeactie=keuzeActie();
		while(keuzeactie!=4){
			switch(keuzeactie){
			case 1: uitvoering.nieuweRekening();
			break;
			case 2: uitvoering.consulterenRekening();
			break; 
			case 3: uitvoering.overschrijvingRekening();
			break;
			default: System.out.println("Gelieve een waarde in te geven tussen 1 en 4");
			
			}
			keuzeactie=keuzeActie();
		}
		System.out.println("Programma wordt afgesloten.");
		
		
	}

	private static int keuzeActie() {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(System.in);
		System.out.println("Geef de keuze in wat u wilt uitvoeren. \n1. Nieuwe Rekening\n2. Saldo consulteren\n3. Overschrijven \n4. Afsluiten");
		int keuze;
		try{
			keuze= scanner.nextInt();
		}catch(InputMismatchException e){
			System.out.println("Gelieve een waarde in te geven tussen 1 en 4. En geen andere waarden.");
			scanner.next(); // clear scanner wrong input
			keuze=0;
		}
		
		return keuze;
	}

}
