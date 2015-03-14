## Lấy danh sách thảo luận ##

### Sắp xếp theo ngày gửi tăng dần ###
  * Phương thức: GET
  * URI: `restPath/comments/resource/a_resource_id?start=start_index&size=max_results`
    * a\_resource\_id: mã tài nguyên
    * start=start\_index: chỉ số của thảo luận đầu tiên được trả về (không bắt buộc, mặc định là 0)
    * size=max\_results: số thảo luận tối đa được trả về (không bắt buộc)
  * Ví dụ: http://localhost:8080/ocwiki/rest/comments/resource/95
```json

{ "result" : [ { "id" : 6,
"message" : "câu này khó...",
"resource" : { "id" : 95,
"name" : "#95",
"namespace" : { "id" : 3,
"name" : "Câu hỏi"
}
},
"revision" : { "id" : 95 },
"timestamp" : "2010-10-08T23:47:16+07:00",
"type" : "commentBean",
"user" : { "avatar" : "1.png",
"fullname" : " admin",
"id" : 1,
"name" : "admin"
}
},
{ "id" : 7,
"message" : "kiến thức lớp 10 thôi mà",
"resource" : { "id" : 95,
"name" : "#95",
"namespace" : { "id" : 3,
"name" : "Câu hỏi"
}
},
"revision" : { "id" : 95 },
"timestamp" : "2010-10-08T23:47:30+07:00",
"type" : "commentBean",
"user" : { "fullname" : " teacher",
"id" : 2,
"name" : "teacher"
}
}
],
"totalCount" : 2
}
```

### Sắp xếp theo ngày gửi giảm dần ###
  * Phương thức: GET
  * URI: `restPath/comments/resource/a_resource_id/latest`
    * a\_resource\_id: mã tài nguyên
    * start=start\_index: chỉ số của thảo luận đầu tiên được trả về (không bắt buộc, mặc định là 0)
    * size=max\_results: số thảo luận tối đa được trả về (không bắt buộc)
  * Ví dụ: http://localhost:8080/ocwiki/rest/comments/resource/95/latest
```json

{ "result" : [ { "id" : 7,
"message" : "kiến thức lớp 10 thôi mà",
"resource" : { "id" : 95,
"name" : "#95",
"namespace" : { "id" : 3,
"name" : "Câu hỏi"
}
},
"revision" : { "id" : 95 },
"timestamp" : "2010-10-08T23:47:30+07:00",
"type" : "commentBean",
"user" : { "fullname" : " teacher",
"id" : 2,
"name" : "teacher"
}
},
{ "id" : 6,
"message" : "câu này khó...",
"resource" : { "id" : 95,
"name" : "#95",
"namespace" : { "id" : 3,
"name" : "Câu hỏi"
}
},
"revision" : { "id" : 95 },
"timestamp" : "2010-10-08T23:47:16+07:00",
"type" : "commentBean",
"user" : { "avatar" : "1.png",
"fullname" : " admin",
"id" : 1,
"name" : "admin"
}
}
],
"totalCount" : 2
}
```