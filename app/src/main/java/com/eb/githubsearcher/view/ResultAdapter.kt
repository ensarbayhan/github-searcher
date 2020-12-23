package com.eb.githubsearcher.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.githubsearcher.models.BaseModel
import com.eb.githubsearcher.models.Repository
import com.eb.githubsearcher.models.User

/**
 * Created by ebayhan on 12/23/20.
 */
class ResultAdapter(private var results: List<BaseModel>,
                    private var listener: ItemClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    companion object {
        const val TYPE_EMPTY = 0
        const val TYPE_USER = 1
        const val TYPE_REPOSITORY = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_EMPTY -> EmptyViewHolder.create(parent)
            TYPE_REPOSITORY -> RepositoryViewHolder.create(parent)
            TYPE_USER -> UserViewHolder.create(parent)
            else -> EmptyViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (results.isEmpty()) {
            return TYPE_EMPTY
        }
        return when (results[position]) {
            is Repository -> TYPE_REPOSITORY
            is User -> TYPE_USER
            else -> TYPE_EMPTY
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is RepositoryViewHolder -> {
                holder.bind(results[position] as Repository, position == 0, listener)
            }
            is UserViewHolder -> {
                holder.bind(results[position] as User, position == getFirstUserPosition(), listener)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (results.isEmpty()) {
            1
        } else {
            results.size
        }
    }

    private fun getFirstUserPosition(): Int {
        return results.indexOfFirst { it is User }
    }

    interface ItemClickListener {
        fun onRepositoryItemClicked(repository: Repository)
        fun onUserItemClicked(user: User)
    }
}