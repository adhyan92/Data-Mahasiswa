package com.example.datamahasiswa

import com.example.datamahasiswa.MahasiswaSource
import com.example.datamahasiswa.model.Mahasiswa

class MahasiswaSource {
    fun loadMahasiswa() : List<Mahasiswa> {
        return listOf<Mahasiswa> (
            Mahasiswa(R.string.Adhyan, R.drawable.wanita),
            Mahasiswa(R.string.Katon, R.drawable.pria),
            Mahasiswa(R.string.Tino, R.drawable.wanita),
            Mahasiswa(R.string.Alif, R.drawable.pria),
            Mahasiswa(R.string.Tom, R.drawable.wanita),
        )
    }
}