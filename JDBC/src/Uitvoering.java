import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Uitvoering {
	public Rekening rekening;
	public Rekening rekeningnummervan;
	public Rekening rekeningnummernaar;
	private  final String URL = "jdbc:mysql://localhost/bank";
	private  final String USER = "cursist";
	private  final String PASSWORD = "cursist";
	//private  final String SQL_select= "SELECT RekeningNr from rekeningen where rekeningnr=?";
	private  final String GETSALDO_SQL="SELECT RekeningNr, Saldo from rekeningen where rekeningnr=?";
	private  final String INSERT_SQL = "insert into rekeningen(rekeningnr) values (?)";
	private static final String DEDUCE_SQL= "UPDATE rekeningen set saldo= saldo-? where rekeningnr=?";
	private static final String COUNT_SQL="UPDATE rekeningen set saldo= saldo+? where rekeningnr=?";
	public Uitvoering(){

	}

	public void overschrijvingRekening() {
		// TODO Auto-generated method stub
		System.out.println("Rekeningnummer waarvan u het geld wilt overschrijven:");
		Scanner scanner= new Scanner(System.in);
		try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement statementDeduce= connection.prepareStatement(DEDUCE_SQL);
				PreparedStatement statementCount= connection.prepareStatement(COUNT_SQL);
				PreparedStatement statementSetRekening =connection.prepareStatement(GETSALDO_SQL)){
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);
			long rekeningnummervanL=vragenRekeningNummer();
			//Setten saldo via rekeningnummer object
			if(rekeningnummervanL==0L) throw new UitvoeringException();
			else{
				statementSetRekening.setLong(1, rekeningnummervanL);
				try(ResultSet resultSet= statementSetRekening.executeQuery()){
					if(resultSet.next()){
						rekeningnummervan= new Rekening(resultSet.getLong("rekeningnr"),resultSet.getBigDecimal("saldo"));
					}
				}
			}
			System.out.println("Rekeningnummer naar waar u wilt overschrijven: ");
			long rekeningnummernaarL= vragenRekeningNummer();
			if(rekeningnummernaarL==rekeningnummervanL) throw new UitvoeringException("Men kan geen geld overschrijven naar dezelfde rekening");
			else if(rekeningnummernaarL==0L) throw new UitvoeringException();
			else{
				statementSetRekening.setLong(1, rekeningnummernaarL);
				try(ResultSet resultSet= statementSetRekening.executeQuery()){
					if(resultSet.next()){
						//System.out.println("Rekeningnummer: "+resultSet.getString("rekeningnr")+"\nSaldo: "+resultSet.getBigDecimal("saldo"));
						rekeningnummernaar= new Rekening(resultSet.getLong("rekeningnr"),resultSet.getBigDecimal("saldo"));
						//System.out.println("rekeningnummer1\n\t"+rekeningnummer1.getRekeningnummer()+"\t"+rekeningnummer1.getSaldo());
					}
				}
			}
			System.out.println("Het saldo dat wilt overschrijven: ");
			BigDecimal saldo= scanner.nextBigDecimal();
			
			System.out.println("rekeningnummervan.getSaldo() "+rekeningnummervan.getSaldo()+ " saldo "+saldo);
			if(rekeningnummervan.getSaldo().compareTo(saldo)==-1) throw new UitvoeringException("Kan deze uitvoering niet uitvoeren");
			else{
				statementDeduce.setLong(2, rekeningnummervan.getRekeningnummer());
				statementDeduce.setBigDecimal(1, saldo);
				statementCount.setLong(2, rekeningnummernaar.getRekeningnummer());
				statementCount.setBigDecimal(1, saldo);
				statementDeduce.executeUpdate();
				statementCount.executeUpdate();
				
			}
			connection.commit();
			
		}catch(UitvoeringException e){
			System.out.println(e.getMessage());
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		

	}



	public void consulterenRekening() {
		// TODO Auto-generated method stub
		System.out.println("Voer de rekeningnummer in dat u wilt consulteren");
		try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement statementConsult =connection.prepareStatement(GETSALDO_SQL)){
			//String nieuwrekeningnummerS=
			long rekeningnummerL= vragenRekeningNummer();
			if(rekeningnummerL==0L) throw new UitvoeringException();
			connection.setAutoCommit(false);
			statementConsult.setLong(1,rekeningnummerL);
			//statementConsult.executeUpdate();
			try(ResultSet resultSet= statementConsult.executeQuery()){
				if(resultSet.next()){
					System.out.println("Rekeningnummer: "+String.format("%012d",(resultSet.getLong("rekeningnr")))+"\nSaldo: "+resultSet.getBigDecimal("saldo"));
				}
			}	
			connection.commit();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}catch(UitvoeringException e){
			
		}
	}

	public void nieuweRekening() {
		// TODO Auto-generated method stub
		//Scanner scanner=new Scanner(System.in);
		System.out.println("Voer de nieuwe rekeningummer in:");
		try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
				PreparedStatement statementInsert =connection.prepareStatement(INSERT_SQL)){
			//String nieuwrekeningnummerS=
			long nieuwerekiningnummerL= vragenRekeningNummer();
			if(nieuwerekiningnummerL==0L) throw new UitvoeringException();
			connection.setAutoCommit(false);
			statementInsert.setLong(1,nieuwerekiningnummerL);
			statementInsert.executeUpdate();
			connection.commit();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}catch(UitvoeringException e){
			
		}
	}

	private long vragenRekeningNummer() {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(System.in);
		long tiencijfers;
		long tweecijfers;
		Long rekeningummerL=0L;
		try{
			String rekeningnummerS= scanner.nextLine();
			rekeningummerL= Long.parseLong(rekeningnummerS);
			if(rekeningnummerS.length()!=12) throw new UitvoeringException("Lengte van 12!");
			//Controleren of het geldig is
			tiencijfers= Long.parseLong(rekeningnummerS.substring(0,10),10);
			tweecijfers= Long.parseLong(rekeningnummerS.substring(10,12),10);
			if(((tiencijfers%97!=tweecijfers))||(rekeningummerL==0L)) throw new UitvoeringException("Is geen geldig rekeningnummer");

		}catch(UitvoeringException e){
			System.out.println(e.getMessage());
			rekeningummerL=0L;
		}
		catch(InputMismatchException e){
			System.out.println("Gelieve een rekeningnummer in te geven dat enkel uit getallen bestaat.");
			rekeningummerL=0L;
		}
		catch(NumberFormatException e){
			System.out.println("Gelieve een rekeningnummer in te geven dat enkel uit getallen bestaat.");
			rekeningummerL=0L;
		}
		return rekeningummerL;
	}

}
