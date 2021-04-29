package mx.cardif.tester;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.xml.bind.DatatypeConverter;

import mx.com.cardif.aes.PasswordIns;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public class EncryptionPKCS {
	
	private static final String ALGORITMO = "AES/CBC/PKCS5Padding";
	private static final String CODIFICACION = "ISO-8859-1";
	Integer intent = 0;

//	public static void main(String[] args) throws Exception{
//		String cadena = leerArchivoTexto();
//		StringBuilder encriptado = new StringBuilder();
//		encriptado.append("{\"encode\": \"").append(new EncryptionPKCS().encrypt(cadena, "AF8032F6EE23648AFB66728C7FA88209")).append("\"}");
////		escribirArchivo(new EncryptionPKCS().encrypt(cadena, "AF8032F6EE23648AFB66728C7FA88209"));
//		sendPost(encriptado.toString());
//	}
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
		System.out.println(new EncryptionPKCS().decrypt("512a35b2fd7fc984ef87c0a8b211e4adc0d4ecd4dd9c04414cc799acff77f2d8n2YfWeW1473oIsJsB3SLamFreToVMEj6x5kpYXVn7RMgjazrRZ612Or74pAUg0K88nssdie4gvkZ2e1XHSK0Tw==", "AF204EB90D17B018E4C5D20200D15F05")); //uat AF8032F6EE23648AFB66728C7FA88209 - prod AF204EB90D17B018E4C5D20200D15F05
	}
	
	private static String leerArchivoTexto() throws IOException {
		StringBuilder respuesta = new StringBuilder();
		String cadena = null;
	    FileReader f = new FileReader("C:\\Tests\\JSON.txt");
	    BufferedReader b = new BufferedReader(f);
	    while((cadena = b.readLine())!=null) {
	    	respuesta.append(cadena);
	    }
	    b.close();
		return respuesta.toString().replaceAll(System.getProperty("line.separator"), "");
	}
	
	public static void escribirArchivo(String contenido){
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("C:\\Tests\\peticionEncriptada.txt");
            pw = new PrintWriter(fichero);
            pw.println(contenido);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}

	public static HttpResponse  sendPost(String cadena, String URL, String token) throws Exception {
		final SSLConnectionSocketFactory sslsf;
		try {
		    sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault(),
		            NoopHostnameVerifier.INSTANCE);
		} catch (NoSuchAlgorithmException e) {
		    throw new RuntimeException(e);
		}

		final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
		        .register("http", new PlainConnectionSocketFactory())
		        .register("https", sslsf)
		        .build();
		

		final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(100);
		HttpClient httpClient = HttpClients.custom()
		        .setSSLSocketFactory(sslsf)
		        .setConnectionManager(cm)
		        .build();
		
		char[] caracteres = cadena.toCharArray();
		HttpPost post = new HttpPost(URL);
		post.setHeader("Content-type", "application/json");
//		post.setHeader("BNPPC-Country-Code", "1");
//		post.setHeader("BNPPC-Channel-Mode", "NI");
//		post.setHeader("BNPPC-Consumer-Credentials-User", "2014042882");
//		post.setHeader("BNPPC-Trace-ReqTimestamp", "2019-08-20T11:27:05.406-06:00");
//		post.setHeader("BNPPC-Trace-Service-Code", "1");
//		post.setHeader("BNPPC-Country-Name", "1");
//		post.setHeader("BNPPC-Trace-CorrelationID", "1");
//		post.setHeader("BNPPC-Trace-Component-Code", "1");
//		post.setHeader("BNPPC-Token-Type", "POST");
//		post.setHeader("BNPPC-Access-Token", "5af7c4d95587be2fc55eb324e61972d6e62e9c56");
//		post.setHeader("BNPPC-Service-Process", "upload");
//		post.setHeader("BNPPC-Consumer-Code", "Test");
//		post.setHeader("BNPPC-Consumer-Name", "Test");
//		post.setHeader("BNPPC-Trace-Service-Operation", "Test");
//		post.setHeader("BNPPC-Trace-Component-Code", "1");
//		post.setHeader("BNPPC-Trace-Component-Name", "Test");
//		post.setHeader("BNPPC-Country-Code", "1");
//		post.setHeader("BNPPC-Country-Name", "Test");
//		post.setHeader("BNPPC-Channel-Code", "1");
//		post.setHeader("BNPPC-Channel-Mode", "Test");
//		post.setHeader("BNPPC-Partner-Code", "4");
//		post.setHeader("BNPPC-Service-Name", "DigitalizacionService");
//		post.setHeader("BNPPC-Consumer-Credentials-User", "2014041771");
		post.setHeader("BNPPC-Country-Code","1");
		post.setHeader("BNPPC-Channel-Mode","NI");
		post.setHeader("BNPPC-Consumer-Code","1");
		post.setHeader("BNPPC-Consumer-Credentials-User","2014042777"); //2014042882 - PROD 2014042775
		post.setHeader("BNPPC-Channel-Code","1");
		post.setHeader("BNPPC-Trace-ReqTimestamp","2019-08-20T11:27:05.406-06:00");
		post.setHeader("BNPPC-Trace-Service-Code","1");
		post.setHeader("BNPPC-Country-Name","1");
		post.setHeader("BNPPC-Trace-CorrelationID","1");
		post.setHeader("BNPPC-Trace-Component-Code","1");
		post.setHeader("BNPPC-Token-Type","POST");
		post.setHeader("BNPPC-Access-Token",token);
		post.setHeader("BNPPC-Trace-MessageID","1");
		post.setHeader("BNPPC-Trace-ConversationID","1");
		post.setHeader("BNPPC-Consumer-Name","1");
		post.setHeader("BNPPC-Consumer-Credentials-Password","1");
		post.setHeader("BNPPC-Trace-Service-Name","1");
		post.setHeader("BNPPC-Service-Process","upload");
		post.setHeader("BNPPC-Trace-Service-Operation","1");
		post.setHeader("BNPPC-Service-Name","DigitalizacionService");
		post.setHeader("BNPPC-Partner-Name","1");
		post.setHeader("BNPPC-Partner-Code","4"); //cambiar ID
		post.setHeader("BNPPC-Trace-Component-Name","1");
		StringEntity entity = new StringEntity(cadena, ContentType.APPLICATION_JSON);
		post.setEntity(entity);
//		HttpClient httpClient = HttpClientBuilder.create().build();
        return httpClient.execute(post);
	}
	
	public static HttpResponse sendPost(String cadena) throws Exception {
		char[] caracteres = cadena.toCharArray();
//		HttpPost post = new HttpPost("http://127.0.0.1:8080/FilesServiceDocuments/v1/Files/uploadDocument");
		HttpPost post = new HttpPost("http://172.17.186.171:8080/FilesServiceDocuments/v1/Files/uploadDocument");
        post.setHeader("Content-type", "application/json");
        post.setHeader("BNPPC-Consumer-Code", "Test");
        post.setHeader("BNPPC-Consumer-Name", "Test");
        post.setHeader("BNPPC-Trace-Service-Operation", "Test");
        post.setHeader("BNPPC-Trace-Component-Code", "1");
        post.setHeader("BNPPC-Trace-Component-Name", "Test");
        post.setHeader("BNPPC-Country-Code", "1");
        post.setHeader("BNPPC-Country-Name", "Test");
        post.setHeader("BNPPC-Channel-Code", "1");
        post.setHeader("BNPPC-Channel-Mode", "Test");
        post.setHeader("BNPPC-Partner-Code", "4");
        post.setHeader("BNPPC-Consumer-Credentials-User", "2014041771");
        post.setHeader("BNPPC-Access-Token", "a096fc9a03da2f830c4786236f1fbea39bbc5fbe");
        
        StringEntity entity = new StringEntity(cadena, ContentType.APPLICATION_JSON);
        post.setEntity(entity);
        HttpClient httpClient = HttpClientBuilder.create().build();
        Utils.imprimir("POST: " + post);
        return httpClient.execute(post);
    }
	
	private String createRandomString(int length) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		while (sb.length() < 32) {
			sb.append(Integer.toHexString(random.nextInt()));
		}
		return sb.toString().substring(0,32);
	}
	
	private  synchronized PasswordIns generaRandomBytesSALT(String strS) {
		PasswordIns insumos = new PasswordIns();
		String stringSALT = createRandomString(32);

			 insumos.setIvSaltString(stringSALT);
			 try {
				 insumos.setIvSaltBytes(DatatypeConverter.parseHexBinary(stringSALT));
			 }catch(Exception e) {
				 generaRandomBytesSALT(strS);
			 }
		 
			 if( insumos.getIvSaltBytes()!=null) {
				 
			 }else {
				 generaRandomBytesSALT(strS);
			 }
	
		 return insumos;
	}
	
	
	public String encrypt(String str, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
	  
	    	PasswordIns passwordInsSalt = generaRandomBytesSALT("SALT");
	    	PasswordIns passwordInsIV = generaRandomBytesSALT("IV");
	    	String saltStr = passwordInsSalt.getIvSaltString();
	    	String ivStr = passwordInsIV.getIvSaltString();
	    	  
	        byte[] salt = passwordInsSalt.getIvSaltBytes();
	       
	        byte[] iv = passwordInsIV.getIvSaltBytes();

	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 100, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
	        
	        IvParameterSpec ivspec = new IvParameterSpec(iv);
	        Cipher cipher = Cipher.getInstance(ALGORITMO);
	        cipher.init(Cipher.ENCRYPT_MODE, secret, ivspec);
	       
	        //byte[] encryptedText = cipher.doFinal(str.getBytes("ISO-8859-1"));
	        
	        byte[] encryptedText = cipher.doFinal(str.getBytes(CODIFICACION));

	        // concatenate salt + iv + ciphertext
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        outputStream.write(salt);
	        outputStream.write(iv);
	        outputStream.write(encryptedText);
	     //   System.out.println("SALT length "+salt.length + " IV "+iv.length);

	        // properly encode the complete ciphertext
	        return saltStr+ivStr+ Base64.encodeBase64String(encryptedText).replaceAll(System.getProperty("line.separator"), "");
	   
	 
	}

	public String decrypt(String str, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
	 
	    	
	    	
	        byte[] ciphertext =  Base64.decodeBase64(str.substring(64, str.length()));

	        byte[] salt =DatatypeConverter.parseHexBinary(  str.substring(0, 32));
	        byte[] iv = DatatypeConverter.parseHexBinary(  str.substring(32, 64));
	        byte[] ct = ciphertext;
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 100, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
	        Cipher cipher = Cipher.getInstance(ALGORITMO);
	        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
	        
	        byte[] plaintext = cipher.doFinal(ct);
	        
	        return new String(plaintext, CODIFICACION);
	    
	}
}
