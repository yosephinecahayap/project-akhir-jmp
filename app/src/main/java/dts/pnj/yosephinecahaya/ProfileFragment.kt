package dts.pnj.yosephinecahaya

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.io.File

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val userInfo = loadUserInfo()
        view.findViewById<TextView>(R.id.nama).text = userInfo["name"]
        view.findViewById<TextView>(R.id.email).text = userInfo["email"]
        view.findViewById<TextView>(R.id.nim).text = userInfo["nim"]
        view.findViewById<TextView>(R.id.kelas).text = userInfo["class"]

        view.findViewById<Button>(R.id.buttonLogout).setOnClickListener {
            handleLogout()
        }

        return view
    }

    private fun loadUserInfo(): Map<String, String> {
        val fileName = "user_info.txt"
        val file = File(requireContext().filesDir, fileName)
        val userInfo = mutableMapOf<String, String>()

        if (file.exists()) {
            file.bufferedReader().useLines { lines ->
                val content = lines.toList()
                if (content.size >= 4) {
                    userInfo["name"] = content[0]
                    userInfo["email"] = content[1]
                    userInfo["nim"] = content[2]
                    userInfo["class"] = content[3]
                }
            }
        }
        return userInfo
    }

    private fun handleLogout() {
        val fileName = "user_info.txt"
        val file = File(requireContext().filesDir, fileName)
        if (file.exists()) {
            file.delete()
        }

        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}