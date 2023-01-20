package com.example.taskapp.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskapp.databinding.ItemOnboardingBinding
import com.example.taskapp.model.OnBoard
import com.example.taskapp.utils.loadImage

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val data = arrayListOf(
        OnBoard("Barcelona", "Barcelona is one of the most widely supported teams in the world.", "https://assets.stickpng.com/images/584a9b3bb080d7616d298777.png"),
        OnBoard("Real Madrid", "Real Madrid is one of the most widely supported teams in the world.", "https://upload.wikimedia.org/wikipedia/sco/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png"),
        OnBoard("Bayern Munich", "Bayern Munich is one of the most widely supported teams in the world. ", "https://upload.wikimedia.org/wikipedia/commons/0/0c/FC_Bayern_M%C3%BCnchen_Logo_2017.png")
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.desc
            binding.image.loadImage(onBoard.image.toString())
            binding.btnStart.isVisible = adapterPosition == data.lastIndex
            binding.btnStart.setOnClickListener{
                onClick()
            }
        }

    }
}