package oeg.upm.isantana.ldvserver.graph;

public class GraphEdge {
	
	protected String id;
	protected String fromid;
	protected String toid;
	protected String label;
	protected double weight;
	
	
	public GraphEdge(String id, String fromid, String toid, double w, String l) {
		super();
		this.id = id;
		this.fromid = fromid;
		this.toid = toid;
		this.weight = w;
		this.label = l;
	}


	public String getId() {
		return id;
	}


	public String getFromid() {
		return fromid;
	}


	public String getToid() {
		return toid;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setFromid(String fromid) {
		this.fromid = fromid;
	}


	public void setToid(String toid) {
		this.toid = toid;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public String getLabel() {
		if(this.label.isEmpty())
			return "..."+this.getId().substring(this.getId().length() - 4);
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	//GENERATED for equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromid == null) ? 0 : fromid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((toid == null) ? 0 : toid.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GraphEdge other = (GraphEdge) obj;
		if (fromid == null) {
			if (other.fromid != null)
				return false;
		} else if (!fromid.equals(other.fromid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (toid == null) {
			if (other.toid != null)
				return false;
		} else if (!toid.equals(other.toid))
			return false;
		return true;
	}
}
