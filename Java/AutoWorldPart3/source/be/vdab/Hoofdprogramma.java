/*
 * Patrick Steens
 */
package be.vdab;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import be.vdab.util.Datum;
import be.vdab.util.Maat;
import be.vdab.util.Volume;
import be.vdab.util.VolumeException;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.Personenwagen;
import be.vdab.voertuigen.Pickup;
import be.vdab.voertuigen.Voertuig;
import be.vdab.voertuigen.Vrachtwagen;

public class Hoofdprogramma {
	public static SortedSet <Voertuig>lijstvoertuigen;
	public static SortedSet <Voertuig>lijstaankoopprijs;
	public static SortedSet <Voertuig>lijstmerken;
	public static SortedSet <Voertuig>lijstvanbestand;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		autoWorld();
	}

	private static void autoWorld() {
		// TODO Auto-generated method stub
		Comparator comp = new AltComparator();
		voertuigenToevoegen();
		voertuigenAfdrukken(lijstvoertuigen);
		voertuigenSorterenAankoop();
		voertuigenAfdrukken(lijstaankoopprijs);
		voertuigenSorterenMerk();
		voertuigenAfdrukken(lijstmerken);
		opslaanLijst(lijstmerken);
		try {
			lijstvanbestand=UitlezenBestand();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static SortedSet<Voertuig> UitlezenBestand() throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
			File file = new File("wagenpark.ser");
	        FileInputStream in = new FileInputStream(file);
	        ObjectInputStream input = new ObjectInputStream(in);
	        return (SortedSet<Voertuig>) input.readObject();
		
		
	}

	private static void opslaanLijst(SortedSet<Voertuig> lijstmerken2) {
		// TODO Auto-generated method stub
		final String GASTENBOEK_PATH = "J:/workspace/Java/AutoWorld/wagenpark.ser"; 
		try { 
			FileOutputStream fileOutputStream = new FileOutputStream(GASTENBOEK_PATH); 
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); 
			objectOutputStream.writeObject(lijstmerken); }
		catch (Exception ex) { 
			System.out.println(ex); }
	}

	private static void voertuigenSorterenMerk() {
		// TODO Auto-generated method stub
		lijstmerken= new TreeSet<Voertuig>();
		lijstmerken=  new TreeSet<Voertuig>(Voertuig.getMerkComparator());
		lijstmerken.addAll(lijstvoertuigen);
	}

	private static void voertuigenSorterenAankoop() {
		// TODO Auto-generated method stub
		lijstaankoopprijs= new TreeSet<Voertuig>();
		lijstaankoopprijs=  new TreeSet<Voertuig>(Voertuig.getAankoopprijsComparator());
		lijstaankoopprijs.addAll(lijstvoertuigen);
		
	}

	private static void voertuigenAfdrukken(SortedSet<Voertuig> afdrukkenlijst) {
		// TODO Auto-generated method stub
		System.out.println("lijst van voertuigen:\n");
		for (Voertuig voertuig:afdrukkenlijst){
			System.out.println(voertuig); 
			System.out.println();
		}
	}

	private static void voertuigenToevoegen()  {
		// TODO Auto-generated method stub
		lijstvoertuigen= new TreeSet<Voertuig>();
		lijstvoertuigen.add(new Personenwagen(Color.orange,"VW",new Datum(1,2,2012),20000,5, new Mens("jef", Rijbewijs.B, Rijbewijs.D)));
		lijstvoertuigen.add(new Personenwagen(Color.black, "Renault", new Datum(15,3,200), 20500,4, new Mens("jef1", Rijbewijs.B)));
		lijstvoertuigen.add(new Pickup(new Volume(5,5,5,Maat.centimeter),Color.cyan,"Nissan",new Datum(14,3,2003),45000,2,new Mens("jef",Rijbewijs.B)));
		lijstvoertuigen.add(new Pickup(new Volume(5,5,5,Maat.centimeter),Color.cyan,"Toyota",new Datum(14,3,2003),45000,2,new Mens("jef",Rijbewijs.B)));
		lijstvoertuigen.add(new Vrachtwagen(new Volume(3,3,3,Maat.meter),500,2,"DAF",new Datum(1,1,3000),30000,3,new Mens("Joseph", Rijbewijs.B)));
		lijstvoertuigen.add(new Vrachtwagen(new Volume(3,3,3,Maat.meter),500,2,"DAF",new Datum(1,1,3000),30000,3,new Mens("Joseph", Rijbewijs.B)));
	}
}
