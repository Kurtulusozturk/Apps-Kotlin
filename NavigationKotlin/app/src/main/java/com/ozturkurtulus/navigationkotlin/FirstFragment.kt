package com.ozturkurtulus.navigationkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.ozturkurtulus.navigationkotlin.databinding.FragmentFirstBinding



class FirstFragment : Fragment() {
    private var binding: FragmentFirstBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    //görünümler view oluşturulduktan sonra çağırılan fonksiyon
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)
        //buttonları bağlıyoruz
        binding!!.button1.setOnClickListener {

            //navigation daki action a ulaşıyoruz
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment("notdefault")
            Navigation.findNavController(it).navigate(action)

        }

    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}