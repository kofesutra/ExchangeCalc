package ru.kofesutra.exchangecalc.start

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import ru.kofesutra.exchangecalc.R
import ru.kofesutra.exchangecalc.model.data

class StartAdapter(private val context: Context, private val listStart: List<data>): RecyclerView.Adapter<StartAdapter.StartViewHolder>() {
// 1) класс StartAdapter наследуем от RecyclerView и добавляем Adapter
    // в треугольных скобках пишем название нашего адаптера, точка, и придумаем название вьюнолдера
    // добавляем ему конструктор ()
    // наводим на слово StartViewHolder нажимаем Alt+Enter и "create class", класс создаём в адаптере
    // наводим курсор на подчёркнутое StartAdapter, Alt+Enter, "Implement Members"

// 3) создаём переменную, но прежде создадим модель например, data и именно её указываем

    class StartViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
// 2) класс StartViewHolder наследуем от RecyclerView.ViewHolder, он требует view
        // в конструкторе StartViewHolder вставляем itemView: View

        // 6) создаём переменные для холдера
        val price: TextView = itemView.findViewById(R.id.item_price) // Отображение цены
        val nameOf: TextView = itemView.findViewById(R.id.item_name) // Отображение названия
        val logo: ImageView = itemView.findViewById(R.id.item_logo) // Отображение картинок
    } // - class StartViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartViewHolder {
// 5)
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return  StartViewHolder(itemView)
    } // - override fun onCreateViewHolder

    override fun onBindViewHolder(holder: StartViewHolder, position: Int) {
        // 7) передаём в холдер данные из data
       // holder.itemView.price.text = listStart[position].USD
        holder.price.text = listStart[position].price
        holder.nameOf.text = listStart[position].symbol

        // Обработкик SVG
        val svgImageLoader = ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()

        // Binding SVG
        holder.logo.load(listStart[position].logo_url, svgImageLoader) {
            size(80, 80)
        }

    } // - override fun onBindViewHolder

    override fun getItemCount(): Int {
// 4) как всегда
        return listStart.size
    }

} // - class StartAdapter