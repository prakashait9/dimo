package com.dapperdrakes.dimo.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * SpokenLang generated by hbm2java
 */

public class SpokenLang implements java.io.Serializable {

	private String iso_3166_1;
	private String name;
	

	public SpokenLang() {
	}

	public SpokenLang(String iso_3166_1) {
		this.iso_3166_1 = iso_3166_1;
	}

	public SpokenLang(String iso_3166_1, String name) {
		this.iso_3166_1 = iso_3166_1;
		this.name = name;
	}

	

	public String getIso_3166_1() {
		return iso_3166_1;
	}

	public void setIso_3166_1(String iso_3166_1) {
		this.iso_3166_1 = iso_3166_1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
}
