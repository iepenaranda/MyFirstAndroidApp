package com.example.mysidneyapprequiem


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysidneyapprequiem.databinding.FragmentListPoiBinding
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.ArrayList

class ListPoiFragment : Fragment() {

    private lateinit var poiViewModel: PoiViewModel

    private var _binding: FragmentListPoiBinding? = null
    private val binding get() = _binding!!

    private lateinit var mPoi: ArrayList<Poi>
    private lateinit var mAdapter: PoiAdapter
    private lateinit var recycler: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("List Poi Fragment", "onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("List Poi Fragment", "onCreateView")
        _binding = FragmentListPoiBinding.inflate(inflater, container, false)
        setupRecycleView()
        generatePoi()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        poiViewModel = ViewModelProvider(requireActivity()).get(PoiViewModel::class.java)
    }

    private fun setupRecycleView() {
        recycler = binding.rvListPoi
        mPoi = arrayListOf()

        recycler.layoutManager = LinearLayoutManager(activity)
        mAdapter = PoiAdapter(mPoi) { poi ->
            poiOnClick(poi)
        }
        recycler.adapter = mAdapter
    }

    private fun poiOnClick(poi: Poi) {
        Log.d(TAG, "Click on: $poi")
        poiViewModel.setPoi(poi)
        findNavController().navigate(R.id.action_listPoiFragment_to_detailFragment)
    }

    private fun generatePoi() {
        val poiString = readPoiJsonFile()
        try {
            val poisJson = JSONArray(poiString)

            for (i in 0 until poisJson.length()) {
                val poiJson = poisJson.getJSONObject(i)
                val poiItem = Poi(
                    poiJson.getString("name"),
                    poiJson.getString("description"),
                    poiJson.getString("imageURL")
                )
                Log.d("MainActivity", "generatePoi: $poiItem")
                mPoi.add(poiItem)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun readPoiJsonFile(): String? {
        var poiString: String? = null
        try {
            val inputStream = requireActivity().assets.open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            poiString = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return poiString
    }

    companion object {

        @JvmStatic
        fun newInstance(): ListPoiFragment {
            return ListPoiFragment()
        }

        private const val TAG = "List Poi Fragment"
    }
}