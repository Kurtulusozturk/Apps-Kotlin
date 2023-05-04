package com.ozturkurtulus.navigationkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ozturkurtulus.navigationkotlin.databinding.FragmentFirstBinding
import com.ozturkurtulus.navigationkotlin.databinding.FragmentSecondBinding

private lateinit var binding: FragmentSecondBinding

class SecondFragment : Fragment() {

    private var binding: FragmentSecondBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSecondBinding.bind(view)

        //veri bohçaları fragmentslar arasında
        arguments?.let {
            val kullaniciAdi = SecondFragmentArgs.fromBundle(it).username
            println(kullaniciAdi)
            binding!!.textView2.text=kullaniciAdi
        }

        binding!!.button2.setOnClickListener {

            val action = SecondFragmentDirections.actionSecondFragmentToFirstFragment()
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}