package mx.cardif.tester;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

import com.google.gson.Gson;

import mx.cardif.tester.TO.DocumentByUploadDTO;
import mx.cardif.tester.TO.Encode;
import mx.cardif.tester.TO.Response;
import mx.cardif.tester.TO.UploadDocumentoVentaReqstDTO;


/**
 * Hello world!
 *
 */
public class App {
	private static final Gson GSON = new Gson();
	private static final String PASSWORD = "AF8032F6EE23648AFB66728C7FA88209";  // cambiar  AF8032F6EE23648AFB66728C7FA88209 // Prod AF204EB90D17B018E4C5D20200D15F05
	private static UploadDocumentoVentaReqstDTO request  = new UploadDocumentoVentaReqstDTO();
	private static final int SEGUNDOS = 1;
	private static Instrumentation instrumentation;
	
	static{
    	request.setIdProductoDocto("40");
    	   	List<String> lista = new ArrayList<String>();
	    	lista.add("05.0102025410006834");
	    	lista.add("05.0102025410006835");
	    	lista.add("05.0102025410006836");
	    	lista.add("05.0102025410006837");
	    	lista.add("05.0102025410006838");
	    	lista.add("05.0102025410006839");
	    	lista.add("05.0102025410006840");
	    	lista.add("05.0102025410006841");
	    	lista.add("05.0102025410006842");
	    	lista.add("05.0102025410006843");
	    	lista.add("05.0102025410006844");
	    	lista.add("05.0102025410006845");
	    	lista.add("05.0102025410006846");
	    	lista.add("05.0102025410006847");
	    	lista.add("05.0102025410006848");
	    	lista.add("05.0102025410006849");
	    	lista.add("05.0102025410006850");
	    	lista.add("05.0102025410006851");
	    	lista.add("05.0102025410006852");
	    	lista.add("05.0102025410006853");
	    	lista.add("05.0102025410006854");
	    	String random = lista.get(new Random().nextInt(lista.size()));
    	request.setPoliza(random);  //UAT - 05.0202025384025015 //Prod - 05.0202025387019276 05.0202025384100493
		List<DocumentByUploadDTO> documentos = new ArrayList<>();
		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\JPG_1M_1.jpg"), "JPG", "1"));
		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\PDF_1M_1.pdf"), "PDF", "2"));
		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\PNG_1M_1.png"), "PNG", "3"));
		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\PDF_1M_5.pdf"), "PDF", "4"));
//		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\JPG_1M_2.jpg"), "JPG", "2"));
//		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\PDF_1M_2.pdf"), "PDF", "2"));
//		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\PNG_1M_2.png"), "PNG", "4"));
//		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\JPG_1M_3.jpg"), "JPG", "3"));
//		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\PDF_1M_3.pdf"), "PDF", "3"));
//		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\PNG_1M_3.png"), "PNG", "3"));
//		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\JPG_1M_4.jpg"), "JPG", "4"));
//		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\PDF_1M_4.pdf"), "PDF", "4"));
//		documentos.add(new DocumentByUploadDTO(encodeFileToBase64Binary("C:\\Users\\Eugenio\\Desktop\\Pruebas\\PDF\\PNG_1M_4.png"), "PNG", "4"));
		request.setDocumentos(documentos);
	}
	
    public static void main( String[] args ) throws Exception
    {
    	Thread.currentThread().setName("Default");
		ExecutorService executor = Executors.newWorkStealingPool();
		List<Callable<String>> callables = Arrays.asList(
				() -> "task1",
				() -> "task2"
//				() -> "task3"
//				() -> "task4",
//				() -> "task5",  
//				() -> "task6",
//				() -> "task7",
//				() -> "task8",
//				() -> "task9",
//				() -> "task10",
//				() -> "task11",
//				() -> "task12",
//				() -> "task13",
//				() -> "task14",
//				() -> "task15"
//				,() -> "task16"
//				,() -> "task17"
//				,() -> "task18"
//				,() -> "task19"
//				,() -> "task20"
				);
		Utils.imprimir("Pruebas de digitalizacion, ejecucion cada " + SEGUNDOS + " segundos, numero de peticiones: " + callables.size());
		
		executor.invokeAll(callables).stream().map(future -> {
			try {
				return future.get();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}).forEach(item -> {
			try {
				Thread.currentThread().sleep(SEGUNDOS * 1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Utils.imprimir("Inicio de ejecucion de hilo: " + item + " hora exacta: " + new SimpleDateFormat("HH:mm:ss.SS").format(new Date()));
			Thread thread = new Thread(execute);
			thread.setName(item);
			thread.start();
			
		});		
    }
    
    public static Runnable execute = () -> {
    	Double timeStart = (double) System.currentTimeMillis();
		
		StringBuilder encriptado = new StringBuilder();
		try{
			encriptado.append("{\"encode\": \"").append(new EncryptionPKCS().encrypt(GSON.toJson(request), PASSWORD)).append("\"}");
		
//		EncryptionPKCS.escribirArchivo(encriptado.toString());
		/**
		 * Configuracion para tibco interno.
		 */
			HttpResponse response = EncryptionPKCS.sendPost(encriptado.toString(),
//					"http://172.17.186.146:8311/CanonicalRestService/v1/CanonicalProcess/getService",
//					"https://www.cardifonline-uat.com.mx:8580/facadeService/v1/facadeProcess/getProcess",
					"https://201.150.93.235:8580/facadeService/v1/facadeProcess/getProcess",
//					"https://www.cardifonline.com.mx:8580/facadeService/v1/facadeProcess/getProcess",
//					"https://201.151.234.138:8580/facadeService/v1/facadeProcess/getProcess", //prod
					"0f6dfa8e21d56d390db8ca0aa8686963b7a4306a");
//					"2ae46e7f8d1d19a6bbfbb9db98aa6ec5f76bffd0"); //prod
		/**
		 * Tibco interno
		 */
		/**
		 * Pruebas
		 */
//			HttpResponse response = EncryptionPKCS.sendPost(encriptado.toString());
		/**
		 * Pruebas
		 */
			InputStream in = response.getEntity().getContent();
			String body = IOUtils.toString(in, "UTF-8");
			try{
				Utils.imprimir("Http Response : " + response);
				Response responseObject = GSON.fromJson(body, Response.class);
				Utils.imprimir("Tiempo de respuesta: "+ (System.currentTimeMillis() - timeStart) + " Milisegundos");
				Utils.imprimir(body);
				Utils.imprimir(new EncryptionPKCS().decrypt(responseObject.getRestResponse().getEncode(), PASSWORD));
			} catch(Exception ex){
				Utils.imprimir("No se puede descrifrar el objeto. El response es el siguiente: \n" + body );
				Utils.imprimir("Tiempo de respuesta: "+ (System.currentTimeMillis() - timeStart) + " Milisegundos");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
    };
    
    private static String encodeFileToBase64Binary(String fileName) {
	    File file = new File(fileName);
	    byte[] encoded;
		try {
			encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
			return new String(encoded, StandardCharsets.US_ASCII);
		} catch (IOException e) {
			return null;
		}
	    
	}
}
