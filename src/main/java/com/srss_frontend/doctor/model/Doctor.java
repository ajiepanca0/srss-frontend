package com.srss_frontend.doctor.model;

import java.time.LocalDate;

public class Doctor {

	public Long idDokter;
	
	public String nomorDokter;

	public String namaDokter;

	public String nomorHp;

	public LocalDate tanggalLahir;

	public String jenisKelamin;

	public String spesialis;

	public String alamat;

	public Doctor() {
	}

	

	public Long getIdDokter() {
		return idDokter;
	}



	public void setIdDokter(Long idDokter) {
		this.idDokter = idDokter;
	}



	public String getNomorDokter() {
		return nomorDokter;
	}



	public void setNomorDokter(String nomorDokter) {
		this.nomorDokter = nomorDokter;
	}



	public String getNamaDokter() {
		return namaDokter;
	}



	public void setNamaDokter(String namaDokter) {
		this.namaDokter = namaDokter;
	}



	public String getNomorHp() {
		return nomorHp;
	}

	public void setNomorHp(String nomorHp) {
		this.nomorHp = nomorHp;
	}

	public LocalDate getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(LocalDate tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	

	public String getSpesialis() {
		return spesialis;
	}

	public void setSpesialis(String spesialis) {
		this.spesialis = spesialis;
	}



	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

}
