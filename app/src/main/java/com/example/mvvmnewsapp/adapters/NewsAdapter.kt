package com.example.mvvmnewsapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.models.Article
import com.example.mvvmnewsapp.databinding.ItemArticlePreviewBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    // 뷰 바인딩 처리
    inner class ArticleViewHolder(val binding: ItemArticlePreviewBinding): RecyclerView.ViewHolder(binding.root)

    /**
     * DiffUtil은 리사이클러뷰의 성능을 한 층 더 개선할 수 있게 해주는 유틸리티 클래스다.
     * 기존의 데이터 리스트와 교체할 데이터 리스트를 비교하여 실질적으로 업데이트가 필요한 아이템을 추려낸다.
     * 백그라운드 스레드에서 작동되기 때문에 메인스레드에 영향을 안준다.
     */
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    /**
     * AsyncListDiffer는 DiffUtil을 더 단순하게 사용할 수 있게 해주는 클래스다.
     * 자체적으로 멀티 쓰레드에 대한 처리가 되어있기 때문에 개발자가 직접 동기화 처리를 할 필요가 없다.
     * ...getCurrentList: adapter에서 사용하는 item 리스트에 접근하고 싶을 때 사용한다.
     * ...submitList(List<T> value): 리스트 데이터를 교체할 때 사용한다.
     */
    val differ = AsyncListDiffer(this, differCallback)
    /**
     *  ViewHolder 객체가 생성되는 곳이다. ViewHolder객체를 return해주면 된다.
     *  화면을 채울 정도의 ViewHolder 객체를 생성하므로 대략 10회 정도의 호출만 발생한다.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsAdapter.ArticleViewHolder {
        return ArticleViewHolder(ItemArticlePreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }
    /**
     * onBindViewHolder는 뷰홀더에 데이터를 결합해주는 함수이다.
     * 스크롤 하면서 맨 위의 뷰 홀더가 아래로 이동(재사용)한다면,
     * position을 사용하여 데이터를 새롭게 갱신하여준다.
     * 스크롤을 하여 새로운 데이터 결합이 필요할 때 마다 호출된다. (계속 스크롤을 한다면 무한정 호출된다...)
     * 그러나 뷰홀더의 재사용으로 인하여 실제 사용되는 뷰의 객체는 10개정도만 사용하는 꼴이다.
     */
    override fun onBindViewHolder(holder: NewsAdapter.ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.binding.apply {
            Glide.with(root).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source?.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            holder.itemView.setOnClickListener {
                // 리스너가 null 이 아닐 경우에만 article 을 반환
                onItemClickListener?.let { it(article) }

            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}