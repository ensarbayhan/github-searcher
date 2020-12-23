package com.eb.githubsearcher.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.githubsearcher.databinding.ItemEmptyBinding
import com.eb.githubsearcher.databinding.ItemRepositoryBinding
import com.eb.githubsearcher.databinding.ItemUserBinding
import com.eb.githubsearcher.models.BaseModel
import com.eb.githubsearcher.models.Repository
import com.eb.githubsearcher.models.User
import com.eb.githubsearcher.util.loadUrl
import com.eb.githubsearcher.util.showIf

/**
 * Created by ebayhan on 12/23/20.
 */

abstract class BaseViewHolder<T : BaseModel>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, showHeader: Boolean, listener: ResultAdapter.ItemClickListener)
}

class UserViewHolder(private var binding: ItemUserBinding) : BaseViewHolder<User>(binding.root) {

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            return UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun bind(item: User, showHeader: Boolean, listener: ResultAdapter.ItemClickListener) {
        binding.apply {
            userName.text = item.login
            userImage.loadUrl(item.avatarUrl)
            headerText.showIf(showHeader)
        }

        binding.root.setOnClickListener {
            listener.onUserItemClicked(item)
        }
    }
}

class RepositoryViewHolder(private var binding: ItemRepositoryBinding) : BaseViewHolder<Repository>(binding.root) {

    companion object {
        fun create(parent: ViewGroup): RepositoryViewHolder {
            return RepositoryViewHolder(ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun bind(item: Repository, showHeader: Boolean, listener: ResultAdapter.ItemClickListener) {
        binding.apply {
            repositoryName.text = item.fullName
            repositoryDescription.text = item.description
            userName.text = item.owner.login
            userImage.loadUrl(item.owner.avatarUrl)
            headerText.showIf(showHeader)
        }

        binding.root.setOnClickListener {
            listener.onRepositoryItemClicked(item)
        }
    }
}

class EmptyViewHolder(binding: ItemEmptyBinding) : BaseViewHolder<BaseModel>(binding.root) {

    companion object {
        fun create(parent: ViewGroup): EmptyViewHolder {
            return EmptyViewHolder(ItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun bind(item: BaseModel, showHeader: Boolean, listener: ResultAdapter.ItemClickListener) {}
}