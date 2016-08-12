/**
 * 
 */
package com.training.enums;

/**
 * @author akaul5
 *
 */
public enum Available {
	UNAVAILABLE(0), AVAILABLE(1);
	
	private int code;
	
	private Available(int code) {
		this.code=code;
	}	
}
