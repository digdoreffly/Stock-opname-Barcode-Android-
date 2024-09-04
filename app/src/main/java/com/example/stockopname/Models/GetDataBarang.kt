package com.example.stockopname.Models

import com.google.gson.annotations.SerializedName
import java.util.*

data class GetDataBarang(
	@SerializedName("status")
	val status: Int,

	@SerializedName("error")
	val error: Boolean,

	@SerializedName("data")
	val data: List<Barang>)

data class Barang (
	@SerializedName("pk_barang_id")
	val pkBarangId: String? = null,

	@SerializedName("id_barang")
	val idBarang: String? = null,

	@SerializedName("qrcode")
	val qrcode: String? = null,

	@SerializedName("fk_satuan_id")
	val fkSatuanId: String? = null,

	@SerializedName("fk_jenisbarang_id")
	val fkJenisbarangId: String? = null,

	@SerializedName("nama_barang")
	val namaBarang: String? = null,

	@SerializedName("stok")
	val stok: Int? = null,

	@SerializedName("created_date")
	val createdDate: Date? = null,

	@SerializedName("created_by")
	val createdBy: Int? = null
)


