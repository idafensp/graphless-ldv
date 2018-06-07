package oeg.upm.isantana.ldvserver.algorithm.gui.parameter;

public class DoubleSliderParameter extends Parameter {
	
	private double minRange;
	private double maxRange;
	private double step;
	private double defaultValue;

	public DoubleSliderParameter(double minRange, double maxRange, double defaultValue, double step) {
		super();
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.defaultValue = defaultValue;
		this.step = step;
	}

	public DoubleSliderParameter(double minRange, double maxRange, double defaultValue, double step, String name, String label) {
		super(name, label);
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.defaultValue = defaultValue;
		this.step = step;
	}

	public double getMinRange() {
		return minRange;
	}

	public double getMaxRange() {
		return maxRange;
	}

	public double getDefaultValue() {
		return defaultValue;
	}

	public void setMinRange(double minRange) {
		this.minRange = minRange;
	}

	public void setMaxRange(double maxRange) {
		this.maxRange = maxRange;
	}

	public void setDefaultValue(double defaultValue) {
		this.defaultValue = defaultValue;
	}

	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}
	
	public String toHtmlString() {
		
		StringBuilder str = new StringBuilder();

		str.append("	  <label for=\""+this.getName()+"\" class=\"mtop\">"+this.getLabel()+"</label>\n");
		str.append("      <div class=\"range range-danger\">\n");
		str.append("           <input type=\"range\" name=\""+this.getName()+"\"  min=\""+this.getMinRange()+"\" max=\"1\" value=\""+this.getDefaultValue()+"\" step=\""+this.getStep()+"\"  onchange=\"id_"+this.getName()+".value=value\">\n");
		str.append("           <output id=\"id_"+this.getName()+"\">"+this.getDefaultValue()+"</output>\n");
		str.append("      </div>\n");
		
		return str.toString();

	}

}
