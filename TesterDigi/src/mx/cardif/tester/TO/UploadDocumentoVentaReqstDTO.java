/**
 * 
 */
package mx.cardif.tester.TO;

import java.io.Serializable;
import java.util.List;

/**********************************************************************************
 * * ID:DIGITAL-CLAIMS *
 * ********************************************************
 * ************************ *
 * 
 * @date: 09 ABRIL 2019 *
 * @author: ISTVAN LUCARIO * Descripción: SERVICIO REST-CLAIMS-161 CARGA DE
 *          DOCUMENTOS (POST) * Clase DTO para parametros del Request *
 ***********************************************************************************/

public class UploadDocumentoVentaReqstDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4686973720754284642L;

	private List<DocumentByUploadDTO> documentos;

	private String poliza;

	private String idProductoDocto;

	public List<DocumentByUploadDTO> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<DocumentByUploadDTO> documentos) {
		this.documentos = documentos;
	}

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public String getIdProductoDocto() {
		return idProductoDocto;
	}

	public void setIdProductoDocto(String idProductoDocto) {
		this.idProductoDocto = idProductoDocto;
	}

	@Override
	public String toString() {
		return "UploadDocumentoVentaReqstDTO [documentos=" + documentos.toString()
				+ ", poliza=" + poliza + ", idProductoDocto=" + idProductoDocto
				+ "]";
	}

	
	
}
