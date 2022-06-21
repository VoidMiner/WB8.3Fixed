package com.ands.wb5weekweb.repository

import android.util.Log
import com.ands.wb5weekweb.model.heroes.DotaHeroesResponse
import com.ands.wb5weekweb.model.heroes.SuperHeroesResponse
import java.io.*


class DotaCacheStorageImpl: DotaCacheStorage {

    override fun saveDotaHeroesToFile(list: List<DotaHeroesResponse>) {

        val fileName = File(FILE_PATH)

        if (!fileName.exists()) fileName.createNewFile()

        val file = FileOutputStream(fileName)

        val outStream = ObjectOutputStream(file)

        list.forEach {
            outStream.writeObject(it)
            Log.e("TAG", it.toString())
        }

        outStream.close()
        file.close()
    }

    override fun getDotaHeroesFromFile(): List<DotaHeroesResponse> {

        val fileName = File(FILE_PATH)

        if (!fileName.exists()) fileName.createNewFile()

        val file = FileInputStream(FILE_PATH)
        val inStream = ObjectInputStream(file)
        val dotaHeroesList = mutableListOf<DotaHeroesResponse>()

        var lines: Long = 0

        try {
            BufferedReader(FileReader(FILE_PATH)).use { reader ->
                while (reader.readLine() != null)
                    lines++
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        try {
            for (i in 0 until lines) {
                val item = inStream.readObject() as DotaHeroesResponse
                dotaHeroesList.add(item)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        inStream.close()
        file.close()
        Log.e("TAG", "$dotaHeroesList")

        return dotaHeroesList.toList()
    }

    companion object {
        private const val FILE_PATH = "/data/data/com.ands.wb5weekweb/cache/dota.txt"
        const val DOTA_HERO = "dota"
    }

}