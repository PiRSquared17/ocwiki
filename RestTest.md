## Lấy dữ liệu ##
  * Phương thức: GET
  * URI: `restPath/tests/a_resource_id`
    * a\_resource\_id: mã tài nguyên của đề thi
  * Ví dụ: `http://localhost:8080/ocwiki/rest/tests/89`
> ```json

{ "result" : { "content" : { "id" : 1648,
"text" : "Theo mẫu đề thi tiếng Anh khối D của Bộ Giáo dục và Đào tạo."
},
"id" : 66,
"name" : "Tiếng Anh khối D 100504204021",
"namespace" : { "id" : 4,
"name" : "Đề thi"
},
"sections" : [ { "content" : { "id" : 1590,
"text" : "<p><strong>Read the following passage and mark the letter A, B, C, or D on your answer sheet to indicate the correct answer to each of the questions from 1 to 10..."
},
"id" : 328,
"questions" : [ { "base" : { "answers" : [ { "content" : { "id" : 376,
"text" : "a conversation"
},
"correct" : true,
"id" : 641
},
{ "content" : { "id" : 377,
"text" : "a movie"
},
"correct" : false,
"id" : 642
},
{ "content" : { "id" : 378,
"text" : "fieldwork"
},
"correct" : false,
"id" : 643
},
{ "content" : { "id" : 379,
"text" : "a newspaper"
},
"correct" : false,
"id" : 644
}
],
"content" : { "id" : 1124,
"text" : "According to the passage, Chaplin got the idea for Modern Times from ______."
},
"id" : 168,
"level" : 1,
"namespace" : { "id" : 3,
"name" : "Câu hỏi"
},
"topics" : [ { "id" : 504,
"name" : "Tiếng Anh",
"namespace" : { "id" : 2,
"name" : "Chủ đề"
}
} ]
},
"baseResource" : { "id" : 168,
"name" : "#168",
"namespace" : { "id" : 3,
"name" : "Câu hỏi"
}
},
"id" : 168,
"mark" : 1.0
},
{ "base" : { "answers" : [ { "content" : { "id" : 612,
"text" : "taken over"
},
"correct" : true,
"id" : 877
},
{ "content" : { "id" : 613,
"text" : "caught up"
},
"correct" : false,
"id" : 878
},
{ "content" : { "id" : 614,
"text" : "used off"
},
"correct" : false,
"id" : 879
},
{ "content" : { "id" : 615,
"text" : "run out"
},
"correct" : false,
"id" : 880
}
],
"content" : { "id" : 1183,
"text" : "The forecast has revealed that the world&rsquo;s reserves of fossil fuel will have ______ by 2015."
},
"id" : 227,
"level" : 2,
"namespace" : { "id" : 3,
"name" : "Câu hỏi"
},
"topics" : [ { "id" : 504,
"name" : "Tiếng Anh",
"namespace" : { "id" : 2,
"name" : "Chủ đề"
}
} ]
},
"baseResource" : { "id" : 227,
"name" : "#227",
"namespace" : { "id" : 3,
"name" : "Câu hỏi"
}
},
"id" : 227,
"mark" : 1.0
}
]
}
],
"testType" : "MUL",
"time" : 180,
"topics" : [ { "id" : 504,
"name" : "Tiếng Anh",
"namespace" : { "id" : 2,
"name" : "Chủ đề"
}
}
],
"type" : "testBean"
} }
```