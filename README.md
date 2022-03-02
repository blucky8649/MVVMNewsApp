# 영어 뉴스 앱 만들기
https://newsapi.org/ 사이트의 api를 활용하여 만든 영어 뉴스 무료 구독 시스템입니다.

### Screen Shot
|뉴스 탐색|기사 저장|기사 삭제 및 복원|키워드 검색|
|---|----|---|---|
|![무한스크롤](https://user-images.githubusercontent.com/83625797/156368624-621bf847-48b2-49ec-bf70-243671e37c85.gif)|![기사 저장](https://user-images.githubusercontent.com/83625797/156368614-c3aa3b32-1cd6-46cf-a96c-840b487a841c.gif)|![데이터 삭제 복원](https://user-images.githubusercontent.com/83625797/156368602-60c1c057-f278-4dab-afc6-8c4a2a6c7582.gif)|![기사 검색 기능](https://user-images.githubusercontent.com/83625797/156368572-2e214826-fd36-4ed1-a21e-1a4ccc0d5677.gif)|





## 사용 기술 스택
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


## 아키텍쳐
아키텍쳐로 다음구조와 같은 MVVM(Model-View-ViewModel)패턴을 사용하였습니다.
![image](https://user-images.githubusercontent.com/83625797/156362905-96f8a59f-9026-4023-85c0-36f6034b966f.png)
* `Activity/Fragment`: 1 액티비티 4프래그먼트구성으로, **네비게이션 컴포넌트를 이용**하여 관리하였습니다.
* `ViewModel`: **LiveData**를 사용하여 **뷰 - 모델간의 최신 데이터를 동기화**하고, http 요청을 통해 얻은 **데이터를 가공하여 저장**하였습니다.
* `Model`: **Room**을 사용하여 로컬 데이터베이스에 데이터를 저장하였습니다. 
* `Remote Data Source`: Retrofit2를 활용한 **REST Api 통신 및 JSON데이터를 객체화**하였습니다.


## 무한 스크롤 기능 소개
> onScrollListener를 사용한 데이터 Pagination 구현
<img src = "https://user-images.githubusercontent.com/83625797/156368624-621bf847-48b2-49ec-bf70-243671e37c85.gif" width = "300">

### 리스너 구현하기
무한 스크롤을 구현하기 위해서는 현재 페이지의 상태를 알아야겠다고 생각했습니다. 그렇기 때문에 다음 코드로 페이지의 현재상태를 `Boolean` 형태로 나타냈습니다.
```kotlin
var isLoading = false // 로딩중인가?
var isLastPage = false // 마지막 페이지인가?
var isScrolling = false // 스크롤중인가?
```
</br>
그러나 위 코드만으로는 다음 페이지를 로드하기위한 조건이 성립되지 않습니다. 무한 스크롤을 구현하기 위해서는 다음과 같은 조건들이 필요합니다.  

```
1) 데이터가 로딩중이 아니어야 한다.
2) 현재 로드된 페이지가 마지막 페이지가 아니어야 한다.
3) 스크롤이 제일 끝에 위치해야 한다.
4) 이제 처음 로드되는 데이터가 아니어야 한다. 
5) 리사이클러뷰의 전체 항목의 수가 한 번에 로드되는 항목의 개수(20)보다 많이야 한다
```
</br>
따라서 리스너 안의 `onScrolled` 콜백 함수를 통해 현재 리사이클러뷰의 상태를 주기적으로 확인하고, Pagination 조건이 갖춰지면 다음 페이지를 불러올 수 있도록 코드를 구성했습니다.  

```kotlin
val scrollListener = object : RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
            isScrolling = true // 현재 스크롤 상태임을 표시
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        // 1) 데이터가 로딩중이 아니어야 한다. & 2) 현재 로드된 페이지가 마지막 페이지가 아니어야 한다.
        val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
        // 3) 스크롤이 제일 끝에 위치해야 한다.
        val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
        // 4) 이제 처음 로드되는 데이터가 아니어야 한다. 
        val isNotAtBeginning = firstVisibleItemPosition >= 0
        // 5) 리사이클러뷰의 전체 항목의 수가 한 번에 로드되는 항목의 개수(20)보다 많이야 한다
        val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
        val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                isTotalMoreThanVisible && isScrolling
        if (shouldPaginate) {
            viewModel.getBreakingNews(COUNTRY) // 조건이 확인되면 다음 페이지를 불러온다.
            isScrolling = false
        }
    }
}
이제 다음과 같이 리사이클러뷰에 리스너를 적용시켜주면 됩니다.
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

### LiveData 갱신하기
기존 데이터를 유지한 채 다음 페이지를 로드하려면, 새로운 데이터를 불러와서 기존에 있는 데이터에 추가해주시면 됩니다.
```kotlin
private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
    if (response.isSuccessful) {
        response.body()?.let { response ->
            breakingNewsPage++ // 스크롤을 내리면 다음 페이지의 기사들이 나올수 있도록 구현
            if (breakingNewsResponse == null) {
                breakingNewsResponse = response
            } else {
                val oldArticles = breakingNewsResponse?.articles // 기존 데이터
                val newArticles = response.articles // 새로운 데이터
                oldArticles?.addAll(newArticles) // 새로운 데이터를 결합시켜준다.
            }
            return Resource.Success(breakingNewsResponse ?: response)
        }
    }
    return Resource.Error(response.message())
}
```
