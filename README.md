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


