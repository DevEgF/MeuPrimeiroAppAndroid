package com.dev.bernardoslailati.meuprimeiroappandroid

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.dev.bernardoslailati.meuprimeiroappandroid.databinding.FragmentBlankBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "name"
private const val ARG_PARAM2 = "age"
private const val ARG_PARAM3 = "isMale"

/**
 * A simple [Fragment] subclass.param1
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    private var _binding: FragmentBlankBinding? = null
    // The property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: Int? = null
    private var param3: Boolean? = null

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BlankFragment", "onDestroyView")
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BlankFragment", "onCreate")
        requireActivity().getString(R.string.hello_blank_fragment)
        requireContext().getString(R.string.hello_blank_fragment)
        context?.getString(R.string.hello_blank_fragment)
        getString(R.string.hello_blank_fragment)

        requireActivity().applicationContext

        context?.let {
            Toast.makeText(
                it,
                getString(R.string.hello_blank_fragment),
                Toast.LENGTH_SHORT
            ).show()
        }

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
            param3 = it.getBoolean(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("BlankFragment", "onCreateView")
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("BlankFragment", "onViewCreated")

        binding.tvFragmentContent.text = getString(
            R.string.name_age_is_male,
            param1,
            param2.toString(),
            if (param3 == true) "Masculino" else "Feminino"
        ).trimIndent()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("BlankFragment", "onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("BlankFragment", "onDetach")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("BlankFragment", "onDestroyView")
    }

    override fun onStart() {
        super.onStart()
        Log.d("BlankFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("BlankFragment", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("BlankFragment", "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("BlankFragment", "onSaveInstanceState")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param name Parameter 1.
         * @param age Parameter 2.
         * @param isMale Parameter 3.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String, age: Int, isMale: Boolean) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, name)
                    putInt(ARG_PARAM2, age)
                    putBoolean(ARG_PARAM3, isMale)
                }
            }
    }
}
