package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.zero.setOnClickListener(this)
        binding.one.setOnClickListener(this)
        binding.two.setOnClickListener(this)
        binding.three.setOnClickListener(this)
        binding.four.setOnClickListener(this)
        binding.five.setOnClickListener(this)
        binding.six.setOnClickListener(this)
        binding.seven.setOnClickListener(this)
        binding.eight.setOnClickListener(this)
        binding.nine.setOnClickListener(this)
        binding.dot.setOnClickListener(this)
        binding.push.setOnClickListener {
            viewModel.push()
        }
        binding.pop.setOnClickListener {
            viewModel.pop()
        }

        viewModel.inputNum.observe(viewLifecycleOwner) {
            binding.inputNum.text = it
        }
        viewModel.sum.observe(viewLifecycleOwner) {
            binding.sum.text = it
        }
    }

    override fun onClick(view: View) {
        (view as? TextView)?.text?.toString()?.let {
            viewModel.onNumClicked(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}