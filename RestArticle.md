## Lấy dữ liệu ##
  * Phương thức: GET
  * URI: `restPath/texts/a_resource_id`
    * a\_resource\_id: mã tài nguyên của bài viết
  * Ví dụ: http://localhost:8080/ocwiki/rest/texts/458
```json

{ "result" :
{ "content" : { "id" : 1696,
"text" : "Farkas Bolyai (1775-1858) đ&atilde; viết thư  cho người con trai l&agrave; Janos Bolyai..."
},
"id" : 466,
"name" : "Đôi điều về hình học phi Ơclit",
"namespace" : { "id" : 0,
"name" : "Chính"
},
"type" : "textArticleBean"
} }
```