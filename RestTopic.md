## Lấy dữ liệu ##
  * Phương thức: GET
  * URI: `restPath/topics/a_resource_id`
    * a\_resource\_id: mã tài nguyên của chủ đề
  * Ví dụ: http://localhost:8080/ocwiki/rest/topics/461
```json

{ "result" :
{ "content" : { "id" : 1718,
"text" : "<p>Tất cả c&aacute;c loại thuộc về khoa học tự nhi&ecirc;n

Unknown end tag for &lt;/p&gt;

"
},
"id" : 512,
"name" : "Khoa học tự nhiên",
"namespace" : { "id" : 2,
"name" : "Chủ đề"
},
"type" : "topicBean"
} }
```