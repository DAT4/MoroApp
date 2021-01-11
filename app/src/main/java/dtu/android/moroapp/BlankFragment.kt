package dtu.android.moroapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dtu.android.moroapp.databinding.FragmentBlankBinding
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testButton.setOnClickListener {
            findNavController().navigate(BlankFragmentDirections.actionBlankFragmentToFrontPageFragment())
        }

    }

}




