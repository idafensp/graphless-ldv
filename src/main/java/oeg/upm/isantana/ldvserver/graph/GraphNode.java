package oeg.upm.isantana.ldvserver.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
	
	protected String id;
	protected int depth;
	protected String label;
	protected String literalValue;
	protected boolean literal;
	protected String uri;
	protected ArrayList<String> types;
	protected int indegree;
	protected int outdegree;

	public GraphNode(String id, String u, int depth, String label) {
		super();
		this.id = id;
		this.depth = depth;
		this.label = label;
		this.literal = false;
		this.uri = u;
	}

	public GraphNode(String id) {
		super();
		this.id = id;
		this.depth = -1;
	}
	
	public String getId() {
		return id;
	}
	public int getDepth() {
		return depth;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getLabel() {
		if((this.label == null) || (this.label.isEmpty()))
		{
			return "..."+this.getUri().substring(this.getUri().length() - 10);
		}
		
		return label;
	}

	public boolean isLiteral() {
		return literal;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLiteral(boolean literal) {
		this.literal = literal;
	}

	public void setLiteral(boolean literal, String val) {
		this.literal = literal;
		this.literalValue = val;
	}

	public String getLiteralValue() {
		return literalValue;
	}

	public void setLiteralValue(String literalValue) {
		this.literalValue = literalValue;
	}

	public String getUri() {
		return uri;
	}

	public ArrayList<String> getTypes() {
		return types;
	}


	public String getTypesString() {
		if(types != null)
		{
			String fTypes =  types.toString().replace("[", "").replace("]", "");
			return fTypes;	
		}
		return "No types found :(";
	}
	

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}

	

	public int getIndegree() {
		return indegree;
	}

	public int getOutdegree() {
		return outdegree;
	}

	public void setIndegree(int inDegree) {
		this.indegree = inDegree;
	}

	public void setOutdegree(int outDegree) {
		this.outdegree = outDegree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		GraphNode other = (GraphNode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
