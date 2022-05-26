package ru.kofesutra.exchangecalc.dialogfragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.kofesutra.exchangecalc.R

class Dialog42 : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Ничего нет, кроме ответа")
                .setMessage("на главный вопрос жизни, вселенной и всего такого:\nэто 42")
                .setIcon(R.drawable.ic_baseline_all_out_24)
                .setPositiveButton("ОК, понятно") {
                        dialog, id ->  dialog.cancel()
                }
                .setNegativeButton("Непонятно") {
                        dialog, id ->
                        val dialogFragment422 = Dialog42_2()
                        val manager = requireActivity().supportFragmentManager
                        dialogFragment422.show(manager, "dialog42")
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


} // - class Dialog42 : DialogFragment()