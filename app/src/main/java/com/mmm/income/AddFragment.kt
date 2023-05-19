package com.mmm.income

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmm.income.databinding.FragmentAddBinding
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup


class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    var isExpense = 0
    lateinit var dbHelper: DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        dbHelper = DBHelper(context)


        binding.btnAdd.setOnClickListener {
            var amt = binding.edtAmt.text.toString().toInt()
            var category = binding.edtCategory.text.toString()
            var note = binding.edtNote.text.toString()
            var model = TransModel(0, amt, category, note, isExpense)

            dbHelper. addTrans(model)

            binding.edtAmt.setText("")
            binding.edtCategory.setText("")
            binding.edtNote.setText("")

        }

        binding.groupChoices.setOnCheckedChangeListener(object : SingleSelectToggleGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: SingleSelectToggleGroup?, checkedId: Int) {

                if (binding.choiceA.id == checkedId) {
                    binding.btnAdd.text = "Add Income"
                    isExpense = 0
                } else if (binding.choiceB.id == checkedId){
                    binding.btnAdd.text = "Add Expense"
                    isExpense = 1
                }

            }

        })
        return binding.root
    }
}