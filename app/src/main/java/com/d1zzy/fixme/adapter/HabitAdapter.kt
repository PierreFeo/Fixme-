import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d1zzy.fixme.R
import com.d1zzy.fixme.data.Habit
import com.d1zzy.fixme.databinding.ItemHabitBinding

class HabitAdapter : ListAdapter<Habit, HabitAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemHabitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(habit: Habit) {
            binding.tvHabitName.text = habit.name
            binding.tvDays.text = "Дней: ${habit.getDaysPassed()}"
            binding.tvMoneySaved.text = "Сэкономил: ${habit.getMoneySaved()} руб"
            binding.tvTimeSaved.text = "Спасённое время: ${habit.getTimeSaved()} мин"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Habit>() {
        override fun areItemsTheSame(oldItem: Habit, newItem: Habit) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Habit, newItem: Habit) =
            oldItem == newItem
    }
}