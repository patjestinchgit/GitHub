//Patrick Steens
import java.math.BigDecimal;


public class Rekening {
	final private Long rekeningnummer;
	private BigDecimal saldo; 
	public Rekening(long rekeningnummervanL, BigDecimal saldo){
		this.rekeningnummer= rekeningnummervanL;
		this.saldo= saldo;
	}

	/**
	 * @return the saldo
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return the rekeningnummer
	 */
	public Long getRekeningnummer() {
		return rekeningnummer;
	}
	
}