package oeg.upm.isantana.ldvserver.algorithm.gui.parameter;

public class InputTextParameter extends Parameter {
	

	private String defaultValue;
	
	public InputTextParameter(String defaultValue) {
		super();
		this.defaultValue = defaultValue;
	}
	
	public InputTextParameter(String defaultValue, String name, String label) {
		super(name, label);
		this.defaultValue = defaultValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	
	public String toHtmlString() {
		
		StringBuilder str = new StringBuilder();
		
		str.append("Still pending");
		
		return str.toString();

	}

}
