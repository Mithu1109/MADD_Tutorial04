package com.example.madd_tutorial04

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class InsertRecordFragment : Fragment() {

    private lateinit var viewModel: MainActivityData
    private var currentRecordId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_insert_record, container, false)
        val tvTitle: TextView = rootView.findViewById(R.id.tvTitle)
        val edtName: EditText = rootView.findViewById(R.id.edtName)
        val edtDOB: EditText = rootView.findViewById(R.id.edtDOB)
        val btnUpdate: Button = rootView.findViewById(R.id.btnUpdate)
        val btnInsert: Button = rootView.findViewById(R.id.btnInsertRecord)

        viewModel = ViewModelProvider(requireActivity())[MainActivityData::class.java]

        // Observe if an item is passed for editing
        viewModel.selectedItem.observe(viewLifecycleOwner) { selected ->
            if (selected != null) {
                tvTitle.text = "Update Record"
                edtName.setText(selected.name)
                edtDOB.setText(selected.dateOfBirth)
                currentRecordId = selected.id
                btnInsert.isEnabled = false
                btnUpdate.isEnabled = true
            } else {
                tvTitle.text = "Insert Record"
                edtName.text.clear()
                edtDOB.text.clear()
                currentRecordId = null
                btnInsert.isEnabled = true
                btnUpdate.isEnabled = false
            }
        }

        btnInsert.setOnClickListener {
            val name = edtName.text.toString().trim()
            val dob = edtDOB.text.toString().trim()
            if (name.isNotEmpty() && dob.isNotEmpty()) {
                val data = MyTable(name, dob)
                insertData(viewModel, requireContext(), data)
                edtName.text.clear()
                edtDOB.text.clear()
                Toast.makeText(requireContext(), "Record Inserted!", Toast.LENGTH_SHORT).show()
            }
        }

        btnUpdate.setOnClickListener {
            val name = edtName.text.toString().trim()
            val dob = edtDOB.text.toString().trim()
            if (name.isNotEmpty() && dob.isNotEmpty() && currentRecordId != null) {
                val updatedRecord = MyTable(name, dob).apply {
                    id = currentRecordId
                }
                viewModel.updateData(requireContext(), updatedRecord)
                viewModel.selectedItem.value = null // reset mode
                Toast.makeText(requireContext(), "Record Updated!", Toast.LENGTH_SHORT).show()
                (activity as? MainActivity)?.loadViewFragment()
            }
        }

        return rootView
    }

    private fun insertData(viewModel: MainActivityData, context: Context, myTable: MyTable) {
        try {
            viewModel.insertData(context, myTable)
            viewModel.setInsertSuccess(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
