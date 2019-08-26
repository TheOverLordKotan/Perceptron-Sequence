package org.ada.ia;

public class PercepSolver {
	private boolean isSolver = false;
	private boolean isUpper = false;
	private float[] pesos = {-1.4f,1.9f,-1.6f};
	private float funcionActivacionBack;
	public boolean isSolver() {
		return isSolver;
	}
	public void setSolver(boolean isSolver) {
		this.isSolver = isSolver;
	}
	public float[] getPesos() {
		return pesos;
	}
	public void setPesos(float[] pesos) {
		this.pesos = pesos;
	}
	public boolean isUpper() {
		return isUpper;
	}
	public void setUpper(boolean isUpper) {
		this.isUpper = isUpper;
	}
	public float getFuncionActivacionBack() {
		return funcionActivacionBack;
	}
	public void setFuncionActivacionBack(float funcionActivacionBack) {
		this.funcionActivacionBack = funcionActivacionBack;
	}
	
	
}
