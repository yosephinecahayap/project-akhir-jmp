package dts.pnj.yosephinecahaya.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_NEWS (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_TITLE TEXT, " +
                    "$COLUMN_CONTENT TEXT, " +
                    "$COLUMN_PATH_IMAGE TEXT)"
        )

        db.execSQL(
            "CREATE TABLE $TABLE_ALUMNI (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_NIM TEXT, " +
                    "$COLUMN_NAMA TEXT, " +
                    "$COLUMN_TEMPAH_LAHIR TEXT, " +
                    "$COLUMN_TANGGAL_LAHIR TEXT, " +
                    "$COLUMN_ALAMAT TEXT, " +
                    "$COLUMN_AGAMA TEXT, " +
                    "$COLUMN_TELEPON TEXT, " +
                    "$COLUMN_TAHUN_MASUK INTEGER, " +
                    "$COLUMN_TAHUN_LULUS INTEGER, " +
                    "$COLUMN_PEKERJAAN TEXT, " +
                    "$COLUMN_JABATAN TEXT)"
        )

        // Insert dummy data
        insertDummyData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NEWS")
        onCreate(db)
    }

    private fun insertDummyData(db: SQLiteDatabase) {
        val titles = listOf(
            "Kesehatan: Konsumsi Sayuran Hijau Dapat Meningkatkan Sistem Imun",
            "Hewan: Populasi Harimau Sumatera Meningkat di Taman Nasional",
            "Teknologi: Perkembangan AI dalam Diagnostik Medis",
            "Lingkungan: Polusi Udara di Kota Besar Meningkat Drastis Akibat Aktivitas Kendaraan"

        )

        val contents = listOf(
            "Penelitian terbaru dari Universitas Harvard menunjukkan bahwa konsumsi sayuran hijau secara rutin dapat meningkatkan sistem kekebalan tubuh. Sayuran seperti bayam, brokoli, dan kale kaya akan vitamin C dan antioksidan, yang berperan penting dalam melawan infeksi dan penyakit. Para ahli menyarankan untuk memasukkan sayuran hijau dalam menu harian demi kesehatan yang lebih baik.",
            "Kabar baik datang dari Taman Nasional Gunung Leuser di Sumatera. Populasi harimau Sumatera yang terancam punah dilaporkan meningkat sebanyak 15% dalam lima tahun terakhir. Program konservasi yang intensif, termasuk patroli hutan dan penyuluhan kepada masyarakat sekitar, dianggap sebagai faktor utama dalam peningkatan ini. Namun, para ahli mengingatkan bahwa upaya pelestarian harus terus dilakukan untuk memastikan kelangsungan hidup spesies ini.",
            "Artificial Intelligence (AI) semakin canggih dan kini mampu mendiagnosis penyakit dengan akurasi yang tinggi. Perusahaan teknologi kesehatan di Jepang baru-baru ini meluncurkan alat diagnostik berbasis AI yang dapat mendeteksi kanker paru-paru sejak dini. Alat ini menggunakan data dari ribuan gambar medis untuk mengidentifikasi pola yang sering kali tidak terdeteksi oleh manusia. Ini diharapkan dapat menyelamatkan lebih banyak nyawa melalui deteksi dini.",
            "Polusi udara di beberapa kota besar di Indonesia dilaporkan meningkat drastis dalam beberapa bulan terakhir. Data dari Badan Meteorologi, Klimatologi, dan Geofisika (BMKG) menunjukkan bahwa tingkat polutan seperti PM2.5 dan PM10 mengalami kenaikan hingga 30% dibandingkan periode yang sama tahun lalu. Para ahli mengaitkan peningkatan ini dengan kembali normalnya aktivitas kendaraan bermotor dan industri setelah pelonggaran pembatasan pandemi."
        )

        val imageResourceIds = listOf(
            "drawable/sayuran",
            "drawable/harimau",
            "drawable/ai_medis",
            "drawable/polusi"
        )

        for (i in titles.indices) {
            val values = ContentValues().apply {
                put(COLUMN_TITLE, titles[i])
                put(COLUMN_CONTENT, contents[i])
                put(COLUMN_PATH_IMAGE, imageResourceIds[i])
            }
            Log.d("DatabaseHelper", "Inserting data: $values")

            val rowId = db.insert(TABLE_NEWS, null, values)
            if (rowId == -1L) {
                Log.e("DatabaseHelper", "Failed to insert row for title: ${titles[i]}")
            } else {
                Log.d("DatabaseHelper", "Inserted row with ID: $rowId")
            }
        }
    }


    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "alumni.db"

        // News table
        const val TABLE_NEWS = "news"
        const val COLUMN_ID = "_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_PATH_IMAGE = "path_image"

        // Alumni table
        const val TABLE_ALUMNI = "alumni"
        const val COLUMN_NIM = "nim"
        const val COLUMN_NAMA = "nama"
        const val COLUMN_TEMPAH_LAHIR = "tempat_lahir"
        const val COLUMN_TANGGAL_LAHIR = "tanggal_lahir"
        const val COLUMN_ALAMAT = "alamat"
        const val COLUMN_AGAMA = "agama"
        const val COLUMN_TELEPON = "telepon"
        const val COLUMN_TAHUN_MASUK = "tahun_masuk"
        const val COLUMN_TAHUN_LULUS = "tahun_lulus"
        const val COLUMN_PEKERJAAN = "pekerjaan"
        const val COLUMN_JABATAN = "jabatan"
    }
}
