package com.example.datamahasiswa

import com.example.datamahasiswa.MahasiswaSource
import com.example.datamahasiswa.model.Mahasiswa

class MahasiswaSource {
    fun loadMahasiswa() : List<Mahasiswa> {
        return listOf<Mahasiswa> (
            Mahasiswa(R.string.Adhyan, R.drawable.wanita, R.string.email_Adhyan),
            Mahasiswa(R.string.Katon, R.drawable.pria, R.string.email_Katon),
            Mahasiswa(R.string.Tino, R.drawable.wanita, R.string.email_Tino),
            Mahasiswa(R.string.Alif, R.drawable.pria, R.string.email_Alif),
            Mahasiswa(R.string.Tom, R.drawable.wanita, R.string.email_Tom),
        )
    }
}