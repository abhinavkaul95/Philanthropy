/**
 * 
 */
package com.training.enums;

/**
 * @author akaul5
 *
 */
public enum ServiceProjects {
	CANCER_CURE(0), PRIMARY_EDUCATION(1), OLD_AGE_HOME(1);
	private int code;
	
	private ServiceProjects(int code) {
		// TODO Auto-generated constructor stub
		this.code=code;
	}
		

	@Override
	public String toString(){
	return ((Integer)this.code).toString();
	}
}
