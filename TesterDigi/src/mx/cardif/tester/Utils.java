package mx.cardif.tester;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static void imprimir(String imprimir){
		System.out.println("(" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS").format(new Date())+ ") " + Thread.currentThread().getName() + " - " + imprimir);
	}
	
}
