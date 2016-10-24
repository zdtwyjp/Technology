package org.tech.base.entity;

public class ZTreeNode extends TreeNode {

	private String name;

	private boolean open;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}
