# ๐ ์์ด ๋ด์ค ์ฑ ๋ง๋ค๊ธฐ
https://newsapi.org/ ์ฌ์ดํธ์ ๋ด์คapi๋ฅผ ํ์ฉํ์ฌ ๋ง๋  ์์ด ๋ด์ค ๋ฌด๋ฃ ๊ตฌ๋ ์์คํ์๋๋ค.

### Screen Shot
|๋ด์ค ํ์|๊ธฐ์ฌ ์ ์ฅ|๊ธฐ์ฌ ์ญ์  ๋ฐ ๋ณต์|ํค์๋ ๊ฒ์|
|---|----|---|---|
|![๋ฌดํ์คํฌ๋กค](https://user-images.githubusercontent.com/83625797/156368624-621bf847-48b2-49ec-bf70-243671e37c85.gif)|![๊ธฐ์ฌ ์ ์ฅ](https://user-images.githubusercontent.com/83625797/156368614-c3aa3b32-1cd6-46cf-a96c-840b487a841c.gif)|![๋ฐ์ดํฐ ์ญ์  ๋ณต์](https://user-images.githubusercontent.com/83625797/156368602-60c1c057-f278-4dab-afc6-8c4a2a6c7582.gif)|![๊ธฐ์ฌ ๊ฒ์ ๊ธฐ๋ฅ](https://user-images.githubusercontent.com/83625797/156368572-2e214826-fd36-4ed1-a21e-1a4ccc0d5677.gif)|





## ๐จโ๐ป ์ฌ์ฉ ๊ธฐ์  ์คํ
### Programming Language
* Kotlin

### Android AAC
  - Navigation
  - ViewModel
  - LiveData
  - View Binding

### Database
 * Room

### IDE
* Android Studio Bumbleblee


## ๐ง ์ํคํ์ณ
์ํคํ์ณ๋ก `MVVM(Model-View-ViewModel)`ํจํด์ ์ฌ์ฉํ์์ต๋๋ค.
![image](https://user-images.githubusercontent.com/83625797/156362905-96f8a59f-9026-4023-85c0-36f6034b966f.png)
* `Activity/Fragment`: 1 ์กํฐ๋นํฐ 4ํ๋๊ทธ๋จผํธ๊ตฌ์ฑ์ผ๋ก, **๋ค๋น๊ฒ์ด์ ์ปดํฌ๋ํธ๋ฅผ ์ด์ฉ**ํ์ฌ ๊ด๋ฆฌํ์์ต๋๋ค.
* `ViewModel`: **LiveData**๋ฅผ ์ฌ์ฉํ์ฌ **๋ทฐ - ๋ชจ๋ธ๊ฐ์ ์ต์  ๋ฐ์ดํฐ๋ฅผ ๋๊ธฐํ**ํ๊ณ , http ์์ฒญ์ ํตํด ์ป์ **๋ฐ์ดํฐ๋ฅผ ๊ฐ๊ณตํ์ฌ ์ ์ฅ**ํ์์ต๋๋ค.
* `Model`: **Room**์ ์ฌ์ฉํ์ฌ ๋ก์ปฌ ๋ฐ์ดํฐ๋ฒ ์ด์ค์ ๋ฐ์ดํฐ๋ฅผ ์ ์ฅํ์์ต๋๋ค. 
* `Remote Data Source`: Retrofit2๋ฅผ ํ์ฉํ **ํด๋ผ์ด์ธํธ์ ์๋ฒ๊ฐ REST Api ํต์  ๊ตฌํ๊ณผ JSON๋ฐ์ดํฐ๋ฅผ ๊ฐ์ฒดํ**ํ์์ต๋๋ค.


## ๋ฌดํ ์คํฌ๋กค ๊ธฐ๋ฅ ์๊ฐ
> onScrollListener๋ฅผ ์ฌ์ฉํ ๋ฐ์ดํฐ Pagination ๊ตฌํ
<img src = "https://user-images.githubusercontent.com/83625797/156368624-621bf847-48b2-49ec-bf70-243671e37c85.gif" width = "300">

### ๋ฆฌ์ค๋ ๊ตฌํํ๊ธฐ
๋ฌดํ ์คํฌ๋กค์ ๊ตฌํํ๊ธฐ ์ํด์๋ ํ์ฌ ํ์ด์ง์ ์ํ๋ฅผ ์์์ผ๊ฒ ๋ค๊ณ  ์๊ฐํ์ต๋๋ค. ๊ทธ๋ ๊ธฐ ๋๋ฌธ์ ๋ค์ ์ฝ๋๋ก ํ์ด์ง์ ํ์ฌ์ํ๋ฅผ `Boolean` ํํ๋ก ๋ํ๋์ต๋๋ค.
```kotlin
var isLoading = false // ๋ก๋ฉ์ค์ธ๊ฐ?
var isLastPage = false // ๋ง์ง๋ง ํ์ด์ง์ธ๊ฐ?
var isScrolling = false // ์คํฌ๋กค์ค์ธ๊ฐ?
```  
  
๊ทธ๋ฌ๋ ์ ์ฝ๋๋ง์ผ๋ก๋ ๋ค์ ํ์ด์ง๋ฅผ ๋ก๋ํ๊ธฐ์ํ ์กฐ๊ฑด์ด ์ฑ๋ฆฝ๋์ง ์์ต๋๋ค. ๋ฌดํ ์คํฌ๋กค์ ๊ตฌํํ๊ธฐ ์ํด์๋ ๋ค์๊ณผ ๊ฐ์ ์กฐ๊ฑด๋ค์ด ํ์ํฉ๋๋ค.  
  
```
1) ๋ฐ์ดํฐ๊ฐ ๋ก๋ฉ์ค์ด ์๋์ด์ผ ํ๋ค.
2) ํ์ฌ ๋ก๋๋ ํ์ด์ง๊ฐ ๋ง์ง๋ง ํ์ด์ง๊ฐ ์๋์ด์ผ ํ๋ค.
3) ์คํฌ๋กค์ด ์ ์ผ ๋์ ์์นํด์ผ ํ๋ค.
4) ์ด์  ์ฒ์ ๋ก๋๋๋ ๋ฐ์ดํฐ๊ฐ ์๋์ด์ผ ํ๋ค. 
5) ๋ฆฌ์ฌ์ดํด๋ฌ๋ทฐ์ ์ ์ฒด ํญ๋ชฉ์ ์๊ฐ ํ ๋ฒ์ ๋ก๋๋๋ ํญ๋ชฉ์ ๊ฐ์(20)๋ณด๋ค ๋ง์ด์ผ ํ๋ค
```
  
๋ฐ๋ผ์ ๋ฆฌ์ค๋ ์์ `onScrolled` ์ฝ๋ฐฑ ํจ์๋ฅผ ํตํด ํ์ฌ ๋ฆฌ์ฌ์ดํด๋ฌ๋ทฐ์ ์ํ๋ฅผ ์ฃผ๊ธฐ์ ์ผ๋ก ํ์ธํ๊ณ , Pagination ์กฐ๊ฑด์ด ๊ฐ์ถฐ์ง๋ฉด ๋ค์ ํ์ด์ง๋ฅผ ๋ถ๋ฌ์ฌ ์ ์๋๋ก ์ฝ๋๋ฅผ ๊ตฌ์ฑํ์ต๋๋ค.    

```kotlin
val scrollListener = object : RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
            isScrolling = true // ํ์ฌ ์คํฌ๋กค ์ํ์์ ํ์
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        // 1) ๋ฐ์ดํฐ๊ฐ ๋ก๋ฉ์ค์ด ์๋์ด์ผ ํ๋ค. & 2) ํ์ฌ ๋ก๋๋ ํ์ด์ง๊ฐ ๋ง์ง๋ง ํ์ด์ง๊ฐ ์๋์ด์ผ ํ๋ค.
        val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
        // 3) ์คํฌ๋กค์ด ์ ์ผ ๋์ ์์นํด์ผ ํ๋ค.
        val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
        // 4) ์ด์  ์ฒ์ ๋ก๋๋๋ ๋ฐ์ดํฐ๊ฐ ์๋์ด์ผ ํ๋ค. 
        val isNotAtBeginning = firstVisibleItemPosition >= 0
        // 5) ๋ฆฌ์ฌ์ดํด๋ฌ๋ทฐ์ ์ ์ฒด ํญ๋ชฉ์ ์๊ฐ ํ ๋ฒ์ ๋ก๋๋๋ ํญ๋ชฉ์ ๊ฐ์(20)๋ณด๋ค ๋ง์ด์ผ ํ๋ค
        val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
        val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                isTotalMoreThanVisible && isScrolling
        if (shouldPaginate) {
            viewModel.getBreakingNews(COUNTRY) // ์กฐ๊ฑด์ด ํ์ธ๋๋ฉด ๋ค์ ํ์ด์ง๋ฅผ ๋ถ๋ฌ์จ๋ค.
            isScrolling = false
        }
    }
}
```
๊ทธ ๋ค์,  ๋ฆฌ์ฌ์ดํด๋ฌ๋ทฐ์ ๋ฐฉ๊ธ ์์ฑํ๋ ๋ฆฌ์ค๋๋ฅผ ์ ์ฉ์์ผ์ฃผ๋ฉด ๋ฉ๋๋ค.

```kotlin
private fun setupReyclerView() {
    newsAdapter = NewsAdapter()
    binding.rvBreakingNews.apply {
        adapter = newsAdapter
        layoutManager = LinearLayoutManager(activity)
        addOnScrollListener(this@BreakingNewsFragment.scrollListener)
    }
}
```

### LiveData ๊ฐฑ์ ํ๊ธฐ
๊ธฐ์กด ๋ฐ์ดํฐ๋ฅผ ์ ์งํ ์ฑ ๋ค์ ํ์ด์ง๋ฅผ ๋ก๋ํ๋ ค๋ฉด, ์๋ก์ด ๋ฐ์ดํฐ๋ฅผ ๋ถ๋ฌ์์ ๊ธฐ์กด์ ์๋ ๋ฐ์ดํฐ์ ์ถ๊ฐํด์ฃผ์๋ฉด ๋ฉ๋๋ค.
```kotlin
private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
    if (response.isSuccessful) {
        response.body()?.let { response ->
            breakingNewsPage++ // ์คํฌ๋กค์ ๋ด๋ฆฌ๋ฉด ๋ค์ ํ์ด์ง์ ๊ธฐ์ฌ๋ค์ด ๋์ฌ์ ์๋๋ก ๊ตฌํ
            if (breakingNewsResponse == null) {
                breakingNewsResponse = response
            } else {
                val oldArticles = breakingNewsResponse?.articles // ๊ธฐ์กด ๋ฐ์ดํฐ
                val newArticles = response.articles // ์๋ก์ด ๋ฐ์ดํฐ
                oldArticles?.addAll(newArticles) // ์๋ก์ด ๋ฐ์ดํฐ๋ฅผ ๊ฒฐํฉ์์ผ์ค๋ค.
            }
            return Resource.Success(breakingNewsResponse ?: response)
        }
    }
    return Resource.Error(response.message())
}
```
</br>

## ๋ฐ์ดํฐ ์ค์์ดํ ์ญ์  ๋ฐ ๋ณต์๊ธฐ๋ฅ ์๊ฐ
> onSwipe ์ฝ๋ฐฑ ํจ์๋ฅผ ํ์ฉํ ๋ฐ์ดํฐ ์ญ์  ๋ฐ ๋ณต์
<img src="https://user-images.githubusercontent.com/83625797/156368602-60c1c057-f278-4dab-afc6-8c4a2a6c7582.gif" width="300">


`itemTouchHeler`์ `onSwife` ์ฝ๋ฐฑํจ์๋ฅผ ํ์ฉํ์์ต๋๋ค. **๊ธฐ์กด ๋ฐ์ดํฐ๋ฅผ ๋ณ์์ ๋ด์๋๊ณ ** ์ญ์  ํ ๋ค์ ๋ณต์ํ๊ณ  ์ถ์ผ๋ฉด **๊ธฐ์กด ๋ฐ์ดํฐ๊ฐ ๋ด๊ธด ๋ณ์๋ฅผ ๋ค์ DB์ ์ฝ์**ํ๋ฉด ๋ฉ๋๋ค. ๋ค์์ ๋ฐ์ดํฐ ์ญ์  ๋ฐ ๋ณต์๊ธฐ๋ฅ์ ๋ํ๋ด๋ ์ฝ๋ฐฑ์ ์ ์ํ๋ ํจ์์๋๋ค.
```kotlin
val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {
    // override fun onMove() ์๋ต..

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val article = newsAdapter.differ.currentList[position] //๊ธฐ์กด ๋ฐ์ดํฐ๋ฅผ ๋ด์์ค๋ค.
        viewModel.deleteArticle(article) // ๋ฐ์ดํฐ ์ญ์ 
        Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
            // ์ญ์  ๋ณต์ ๊ธฐ๋ฅ ๊ตฌํ
            setAction("Undo") {
                viewModel.saveArticle(article) // ์ค๋ต๋ฐ์ undo๋ฅผ ๋๋ฅด๊ฒ ๋๋ค๋ฉด, ๊ธฐ์กด ๋ฐ์ดํฐ๋ฅผ ๋ค์ ์ฝ์ํ๋ค.
            }
            show()
        }
    }
}
```

</br>

## ๊ธฐ์ฌ ํค์๋ ๊ฒ์๊ธฐ๋ฅ ์๊ฐ
>EditText.addTextChangedListener ํ์ฉ

<img src ="https://user-images.githubusercontent.com/83625797/156368572-2e214826-fd36-4ed1-a21e-1a4ccc0d5677.gif" width = "300">

์ ํ์ฉ ์์ ์ ๊ฐ์ด `EditText`์ ๊ฐ์ด ๋ณํ  ๋๋ง๋ค ์ง์ํด์ ๊ฒฐ๊ณผ๊ฐ์ ์ฌ์์ฒญํด์ฃผ์ด์ผ ํฉ๋๋ค. ๋ฐ๋ผ์ `EditText`์ `addTextChangedListener`ํจ์๋ฅผ ์ฌ์ฉํ์๊ณ , **๋ฐ์ดํฐ์ ๋ณ๊ฒฝ์ด ์ด๋ฃจ์ด ์ง๋๋ง๋ค ์๋ก์ด ๋ด์ค ๋ฐ์ดํฐ๋ฅผ ์์ฒญ**ํฉ๋๋ค.

>๋จ, **์ฆ์ ์๋ ฅ์ผ๋ก ์ธํ ๊ณผ์์ฒญ ์ํ๋ฅผ ๋ฐฉ์ง**ํ๊ธฐ ์ํด, ์ฝ๋ฃจํด์ ํ์ฉํ์ฌ **5์ด๊ฐ์ ์์ฒญ ๋๋ ์ด**๋ฅผ ์ฃผ์ด ํด๋น ๋ฌธ์ ๋ฅผ ํด๊ฒฐํฉ๋๋ค.  

```kotlin
binding.etSearch.addTextChangedListener { editable ->
    job?.cancel()
    job = MainScope().launch {
        delay(FIVE_SECONDS_DELEY) // 5์ด๊ฐ์ ๋น๋๊ธฐ ๋๋ ์ด
        editable?.let {
            if (editable.toString().isNotEmpty()) {
                // ํ์คํธ์ฐฝ์ด ๋น์ด์์ง ์๋ ํ, ํ์คํธ ๋ณ๊ฒฝ์ด ์ด๋ฃจ์ด์ง ๋ 5์ด ๊ฐ๊ฒฉ์ผ๋ก ๋ฐ์ดํฐ๋ฅผ ์์ฒญํ๋ค.
                viewModel.searchNews(editable.toString())
            }
        }
    }
}
```
