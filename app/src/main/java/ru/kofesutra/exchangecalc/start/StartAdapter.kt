package ru.kofesutra.exchangecalc.start

import android.content.Context
import android.content.Intent
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
import ru.kofesutra.exchangecalc.SecondActivity
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
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return  StartViewHolder(itemView)
    } // - override fun onCreateViewHolder

    override fun onBindViewHolder(holder: StartViewHolder, position: Int) {
        // 7) передаём в холдер данные из data
        val indexPlus = listStart[position].price.indexOf('.')
        holder.price.text = listStart[position].price.substring(0 ..indexPlus+5)
        holder.nameOf.text = listStart[position].symbol
        val logoTemp: String = listStart[position].logo_url
        val indexPlusHigh = listStart[position].high.indexOf('.')

        // Обработкик SVG
        val svgImageLoader = ImageLoader.Builder(context)
            .components { add(SvgDecoder.Factory()) }.build()
        // Binding SVG
        holder.logo.load(listStart[position].logo_url, svgImageLoader) {
            size(80, 80)
        } // - Обработкик SVG Binding SVG

        holder.itemView.setOnClickListener(View.OnClickListener() {
//                Toast.makeText(holder.itemView.context, "Clicked $toClicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(holder.itemView.context, SecondActivity::class.java)
            intent.putExtra(SecondActivity.intentPrice, holder.price.text)
            intent.putExtra(SecondActivity.intentName, holder.nameOf.text)
            intent.putExtra(SecondActivity.intentLogo, logoTemp)
            intent.putExtra(SecondActivity.intentHigh, listStart[position].high.substring(0 ..indexPlusHigh+5))
//            intent.putExtra(SecondActivity.intentPercentDay, listStart[position].price_change_pct)
            holder.itemView.context.startActivity(intent)
        })

    } // - override fun onBindViewHolder

    override fun getItemCount(): Int {
// 4) как всегда
        return listStart.size
    }

} // - class StartAdapter
