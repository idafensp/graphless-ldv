package oeg.upm.isantana.ldvserver.algorithm.gui.parameter;

public class IntegerSliderParameter extends Parameter {
	
	private int minRange;
	private int maxRange;
	private int step;
	private int defaultValue;

	public IntegerSliderParameter(int minRange, int maxRange, int defaultValue, int step) {
		super();
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.defaultValue = defaultValue;
		this.step = step;
	}

	public IntegerSliderParameter(int minRange, int maxRange, int defaultValue, int step, String name, String label) {
		super(name, label);
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.defaultValue = defaultValue;
		this.step = step;
	}

	public int getMinRange() {
		return minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public int getDefaultValue() {
		return defaultValue;
	}

	public void setMinRange(int minRange) {
		this.minRange = minRange;
	}

	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}

	public void setDefaultValue(int defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	public String toHtmlString() {
		
		StringBuilder str = new StringBuilder();

		str.append("	  <label for=\""+this.getName()+"\" class=\"mtop\">"+this.getLabel()+"</label>\n");
		str.append("      <div class=\"range range-danger\">\n");
		str.append("           <input type=\"range\" name=\""+this.getName()+"\"  min=\""+this.getMinRange()+"\" max=\""+this.getMaxRange()+"\" value=\""+this.getDefaultValue()+"\" step=\""+this.getStep()+"\"  onchange=\"id_"+this.getName()+".value=value\">\n");
		str.append("           <output id=\"id_"+this.getName()+"\">"+this.getDefaultValue()+"</output>\n");
		str.append("      </div>\n");
		
		return str.toString();

	}

}
