package dts.pnj.yosephinecahaya.placeholder

import dts.pnj.yosephinecahaya.R
import android.R.id


object PlaceholderContent {

    val ITEMS: MutableList<PlaceholderItem> = ArrayList()


    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    private val COUNT = 25

    init {
        for (i in 1..COUNT) {
            addItem(createPlaceholderItem(i))
        }
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createPlaceholderItem(position: Int): PlaceholderItem {
        return PlaceholderItem(
            id = position.toString(),
            content = "Item $position",
            details = makeDetails(position),
            imageResource = R.drawable.ic_launcher_foreground
        )
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }


    data class PlaceholderItem(
        val id: String,
        val content: String,
        val details: String,
        val imageResource: Int
    ) {
        override fun toString(): String = content
    }
}