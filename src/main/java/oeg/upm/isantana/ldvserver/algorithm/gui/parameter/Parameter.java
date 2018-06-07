package oeg.upm.isantana.ldvserver.algorithm.gui.parameter;

public abstract class Parameter {

	protected String name;
	protected String label;
	
	public Parameter() {
		super();
		this.name = "...";
		this.label = "...";
	}
	
	public Parameter(String name, String label) {
		super();
		this.name = name;
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public abstract String toHtmlString();
	
}
