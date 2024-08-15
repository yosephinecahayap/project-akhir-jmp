package dts.pnj.yosephinecahaya

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import dts.pnj.yosephinecahaya.databinding.FragmentBeritaBinding

class MyBeritaRecyclerViewAdapter(
    private val values: List<NewsItem>
) : RecyclerView.Adapter<MyBeritaRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentBeritaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.content.text = item.content

        val imageResource = when (item.pathImage) {
            "drawable/ai_medis" -> R.drawable.ai_medis
            "drawable/harimau" -> R.drawable.harimau
            "drawable/polusi" -> R.drawable.polusi
            "drawable/sayuran" -> R.drawable.sayuran
            else -> R.drawable.ic_berita
        }
        holder.image.setImageResource(imageResource)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailNewsActivity::class.java).apply {
                putExtra("EXTRA_TITLE", item.title)
                putExtra("EXTRA_CONTENT", item.content)
                putExtra("EXTRA_IMAGE_PATH", item.pathImage)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentBeritaBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val content = binding.content
        val image =binding.itemImage

        override fun toString(): String {
            return super.toString() + " '" + content.text + "'"
        }
    }

}