package com.techtown.api

data class ResultGetSearchNews(
    var lastBuildDate: String = "",
    var total: Int = 0,
    var start: Int = 0,
    var display: Int = 0,
    var items: List<Items>
)

data class Items(
    var title: String = "",
    var originallink: String = "",
    var link: String = "",
    var description: String = "",
    var pubDate: String = ""
)
//title	String	문서 제목
//contents	String	문서 본문 중 일부
//url	String	문서 URL
//datetime	Datetime	문서 글 작성시간. ISO 8601. [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
//data class ResultGetSearchNews(
//    var title:String = "",
//    var contents:String = "",
//    var url:String=""
//)