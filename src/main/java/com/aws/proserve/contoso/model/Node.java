package com.aws.proserve.contoso.model;

public class Node {

	private String name;

	@Override
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		}

		if (obj == null)
			return false;

		if (!(obj instanceof Node)) {
			return false;
		}

		Node nodeObj = (Node) obj;

		return name.equals(nodeObj.getName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
	    int result = 1;
	    result = prime * result + ((name == null) ? 0 : name.hashCode());
	    return result;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
