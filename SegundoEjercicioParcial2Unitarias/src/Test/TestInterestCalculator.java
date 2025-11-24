package Test;
import Ejercicio.InterestCalculator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class TestInterestCalculator {
	// Atributo
	private InterestCalculator calculator;
	// Métodos de configuración y limpieza
	@BeforeEach
	public void setUp() {
		calculator = new InterestCalculator();
	}

	@AfterEach
	public void tearDown() {
		calculator = null;
	}
	// Pruebas para calculateCompound():
	
	// Resultado correcto para valores simples
	@Test
	public void testCalculateCompound_SimpleValues() {
		double result = calculator.calculateCompound(1000, 0.1, 1);// 1000 * (1 + 0.1)^1 = 1100
		assertEquals(1100, result, 0.01);// Tolerancia de 0.01
	}
	// Comportamiento con valores grandes
	@Test
	public void testCalculateCompound_LargeValues() {
		double result = calculator.calculateCompound(1000000, 0.05, 10);// 1000000 * (1 + 0.05)^10 = 1628894.63
		assertEquals(1628894.63, result, 0.01);// Tolerancia de 0.01
	}
	// Excepciones para valores negativos
	
	// Principal < 0
	@Test
	public void testCalculateCompound_NegativePrincipal() {
		assertThrows(IllegalArgumentException.class, () -> {
			calculator.calculateCompound(-1000, 0.1, 1); // Principal negativo
		});
	}
	// Rate < 0
	@Test
	public void testCalculateCompound_NegativeRate() {
		assertThrows(IllegalArgumentException.class, () -> {
			calculator.calculateCompound(1000, -0.1, 1);// Rate negativo
		});
	}
	// Years < 0
	@Test
	public void testCalculateCompound_NegativeYears() {
		assertThrows(IllegalArgumentException.class, () -> {
			calculator.calculateCompound(1000, 0.1, -1);// Years negativo
		});
	}
	// Pruebas para monthlyPayment():
	
	// pueba de resultado aproximado
	@Test
	public void testMonthlyPayment_ApproximateValue() {
		double result = calculator.monthlyPayment(10000, 0.05, 12);// Resultado esperado aproximado: 856.07
		assertEquals(856.07, result, 0.01);// Tolerancia de 0.01
	}
	// Validar excepciones para valores 0 o negativos
	@Test
	public void testMonthlyPayment_InvalidValues() {
		assertThrows(IllegalArgumentException.class, () -> {
			calculator.monthlyPayment(0, 0.05, 12);// Principal 0
		});
		assertThrows(IllegalArgumentException.class, () -> {
			calculator.monthlyPayment(10000, -0.05, 12);// Rate negativo
		});
		assertThrows(IllegalArgumentException.class, () -> {
			calculator.monthlyPayment(10000, 0.05, 0);// Months 0
		});
	}
	// Verificar que un préstamo a más meses, produce una cuota menor
	@Test
	public void testMonthlyPayment_MoreMonthsLowerPayment() {
		double payment12Months = calculator.monthlyPayment(10000, 0.05, 12);// Pago mensual para 12 meses
		double payment24Months = calculator.monthlyPayment(10000, 0.05, 24);// Pago mensual para 24 meses
		assertTrue(payment24Months < payment12Months);// Verifica que el pago para 24 meses es menor que para 12 meses
	}
}
