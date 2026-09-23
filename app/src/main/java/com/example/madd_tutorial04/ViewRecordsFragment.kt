package com.example.madd_tutorial04

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewRecordsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_view_records, container, false)
        val rv: RecyclerView = rootView.findViewById(R.id.rvList)
        rv.layoutManager = LinearLayoutManager(requireContext())

        val viewModel: MainActivityData =
            ViewModelProvider(requireActivity())[MainActivityData::class.java]

        viewModel.loadData(requireContext())

        viewModel.listMyTable.observe(viewLifecycleOwner) { list ->
            val adapter = MyDataAdapter(
                data = list ?: emptyList(),
                onUpdateClicked = { recordToUpdate ->
                    // Set selected item and switch to Insert/Edit fragment
                    viewModel.selectedItem.value = recordToUpdate
                    (activity as? MainActivity)?.loadInsertFragment()
                },
                onDeleteClicked = { recordToDelete ->
                    viewModel.deleteData(requireContext(), recordToDelete)
                    Toast.makeText(requireContext(), "Deleted ${recordToDelete.name}", Toast.LENGTH_SHORT).show()
                }
            )
            rv.adapter = adapter
        }

        return rootView
    }
}
