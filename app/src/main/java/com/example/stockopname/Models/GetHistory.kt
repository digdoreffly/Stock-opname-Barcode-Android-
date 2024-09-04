package com.example.stockopname.Models

import com.google.gson.annotations.SerializedName

data class GetHistory(
	@SerializedName("data")
	val data: List<DataItem?>? = null,
	@SerializedName("error")
	val error: Boolean? = null
)

data class DataItem(
	@SerializedName("id_barang")
	val idBarang: String? = null,
	@SerializedName("nomerbarang")
	val nomorbarang: String? = null,
	@SerializedName("nama_barang")
	val namaBarang: String? = null,
	@SerializedName("pk_transaksi_masuk_id")
	val pkTransaksiMasukId: String? = null,
	@SerializedName("tanggal")
	val tanggal: String? = null,
	@SerializedName("jumlah_masuk")
	val jumlahMasuk: String? = null
)

