package ru.kofesutra.exchangecalc.dialogfragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.kofesutra.exchangecalc.R

class Dialog42_2 : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Повторяем: ничего нет, кроме ответа")
                .setMessage("на главный вопрос жизни, вселенной и всего такого:\nэто 42")
                .setIcon(R.drawable.ic_baseline_all_out_24)
                .setPositiveButton("ОК, понятно") {
                        dialog, id ->  dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
} // - class Dialog42 : DialogFragment()