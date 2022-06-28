package com.dicoding.submission2
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission2.databinding.HomePageBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

   private val listUser = ArrayList<PostResponse>()
    private var onItemClickedCallback: OnItemClickedCallback? =null

    fun setOnItemClickCallback(onItemClickedCallback: OnItemClickedCallback){
        this.onItemClickedCallback = onItemClickedCallback

    }

   @SuppressLint("NotifyDataSetChanged")
   fun setList(users: ArrayList<PostResponse>){
       listUser.clear()
       listUser.addAll(users)
       notifyDataSetChanged()
   }
    inner class ListViewHolder(private val binding: HomePageBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: PostResponse){
            binding.root.setOnClickListener {
                onItemClickedCallback?.onItemClick(user)
            }
            binding.apply {
                Glide.with (itemView).load(user.avatar_url).centerCrop().into(photo)
                username.text = user.login
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = HomePageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
    }
    override fun getItemCount(): Int = listUser.size

    interface OnItemClickedCallback{
        fun onItemClick(data: PostResponse)

    }
}