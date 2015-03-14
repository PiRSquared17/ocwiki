## Lấy dữ liệu ##
  * Phương thức: GET
  * URI: `restPath/questions/a_resource_id`
    * a\_resource\_id: mã tài nguyên của câu hỏi
  * Ví dụ: `http://localhost:8080/ocwiki/rest/questions/89`
> > ```json

{ "result" : {
"answers" : [
{ "content" : { "id" : 1786,
"text" : "<p>national

Unknown end tag for &lt;/p&gt;

"
},
"correct" : false,
"id" : 1290
},
{ "content" : { "id" : 1791,
"text" : "<p>nationality

Unknown end tag for &lt;/p&gt;

"
},
"correct" : true,
"id" : 1294
},
{ "content" : { "id" : 1792,
"text" : "<p>nationalized

Unknown end tag for &lt;/p&gt;

"
},
"correct" : false,
"id" : 1295
},
{ "content" : { "id" : 1789,
"text" : "<p>nation

Unknown end tag for &lt;/p&gt;

"
},
"correct" : false,
"id" : 1293
}
],
"content" : {
"id" : 1793,
"text" : "<p>If you are not Japanese, so what _______ are you?

Unknown end tag for &lt;/p&gt;

"
},
"id" : 597,
"level" : 4,
"namespace" : {
"id" : 3,
"name" : "Câu hỏi"
},
"topics" : [ { "id" : 504,
"name" : "Tiếng Anh",
"namespace" : { "id" : 2,
"name" : "Chủ đề"
}
} ],
"type" : "baseQuestionBean"
} }```