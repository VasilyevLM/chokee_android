package com.konterraweb.chokee

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.konterraweb.chokee.network.ServiceRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_about, container, false)
        val logoutBtn: Button = view.findViewById(R.id.logoutBtn)

        logoutBtn.setOnClickListener(logout)
        return view
    }

    private val logout = View.OnClickListener {
        val user = (requireActivity().application as MyApp).user
        if(user != null) {
            ServiceRepository().logout(user, object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {}
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {}
            })
            val pref: SharedPreferences.Editor = requireActivity()
                .getSharedPreferences("User", Context.MODE_PRIVATE)
                .edit()
            pref.clear()
            pref.commit()
        }
        val intent = Intent(requireActivity().applicationContext, LoginActivity::class.java)
        startActivity(intent)
    }
}