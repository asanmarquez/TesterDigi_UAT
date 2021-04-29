package mx.cardif.tester.TO;

import java.io.Serializable;

public class DocumentByUploadDTO implements Serializable{

	private static final long serialVersionUID = 4686973720754284643L;
	
	public DocumentByUploadDTO() {
		super();
	}

	public DocumentByUploadDTO(String documento, String formato,
			String tipoDocumento) {
		super();
		this.documento = documento;
		this.formato = formato;
		this.tipoDocumento = tipoDocumento;
	}

	private String documento;	
	
	private String formato;
	
	private String tipoDocumento;
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	@Override
	public String toString() {
		return "DocumentByUploadDTO [documento=" + documento != null && !documento.isEmpty() && documento.length() > 100 ? documento.substring(0, 100) : documento + ", formato="
				+ formato + ", tipoDocumento=" + tipoDocumento + "]";
	}
	
	
}
