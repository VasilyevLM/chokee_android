package com.konterraweb.chokee.ui.main

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.konterraweb.chokee.MyApp
import com.konterraweb.chokee.R
import com.konterraweb.chokee.Statuses

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        (requireActivity().application as MyApp).user?.let {
            val statusText = root.findViewById<TextView>(R.id.statusText)
            val myImageStatus = root.findViewById<ImageView>(R.id.myImageStatus)
            if(Statuses.image.containsKey(it.status)) {
                statusText.text = it.status
                val imageName = Statuses.image[it.status]
                val imageRes = resources.getIdentifier(
                        "@drawable/$imageName",
                        null,
                        requireActivity().packageName
                    )
                myImageStatus.setImageResource(imageRes)
            }
        }
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}