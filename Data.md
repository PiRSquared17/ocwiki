Các lớp chính:

![https://lh5.googleusercontent.com/_6irjfzkk21k/TWkrqEfXDmI/AAAAAAAADlM/B9sVmQpt1O0/main%20classes.jpg](https://lh5.googleusercontent.com/_6irjfzkk21k/TWkrqEfXDmI/AAAAAAAADlM/B9sVmQpt1O0/main%20classes.jpg)

  * **Resource**: lớp chung nhất biểu diễn mọi loại tài nguyên trong wiki.
  * **Revision**: một phiên bản của tài nguyên, chứa nội dung và các metadata về lần sửa đổi đã được thực hiện.
  * **Article**: dữ liệu chứa trong một phiên bản của tài nguyên.
  * **User**: biểu diễn người sử dụng.
  * **ResourceCustomization**: sự tuỳ biến của người sử dụng đối với tài nguyên (VD: thích/không thích, khó/dễ,...)
  * **Comment**: bình luận/thảo luận
  * **CommentCustomization**: tuỳ biến của người dùng với bình luận (thích/không thích)
  * **FacebookAccount**: tài khoản Facebook có thể sử dụng để đăng nhập vào hệ thống.
  * **OpenIDAccount**: tài khoản OpenID có thể sử dụng để đăng nhập vào hệ thống.
  * **Preference**: tuỳ chọn của người sử dụng.

Các Article chia ra thành nhiều loại cụ thể như sau:

![https://lh5.googleusercontent.com/_6irjfzkk21k/TWkmiPND5NI/AAAAAAAADko/kiVSaQVp_Ns/articles.jpg](https://lh5.googleusercontent.com/_6irjfzkk21k/TWkmiPND5NI/AAAAAAAADko/kiVSaQVp_Ns/articles.jpg)

  * **Topic**: chủ đề.
  * **CategorizableTopic**: các lớp có thể phân loại vào các chủ đề (bổ sung thuộc tính topics.
  * **File**: tập tin.
  * **BaseArticle**: lớp trừu tượng chung cho hầu hết các lớp khác.
  * **Question**: lớp trừu tượng cho mọi câu hỏi.
  * **MultichoiceQuestion**: câu hỏi trắc nghiệm nhiều lựa chọn.
  * **BlankFillingQuestion**: câu hỏi trắc nghiệm điền từ vào chỗ trống.
  * **ShortAnswerQuestion**: câu hỏi trắc nghiệm câu trả lời ngắn.
  * **MatchingQuestion**: câu hỏi trắc nghiệm ghép cặp.
  * **Test**: bài kiểm tra.
  * **TextArticle**: bái viết thông thường dạng văn bản.
  * **Solution**: bài giải chi tiết cho một câu hỏi.
  * **TestStructure**: cấu trúc đề kiểm tra.

Một đề kiểm tra được chia làm nhiều phần, mỗi phần chứa nhiều câu hỏi. Nếu người sử dụng không chia phần thì một phần mặc định sẽ được tạo ra để chứa các câu hỏi.

![https://lh5.googleusercontent.com/_6irjfzkk21k/TWkminhN9II/AAAAAAAADk0/wdwbznYJuDk/test.jpg](https://lh5.googleusercontent.com/_6irjfzkk21k/TWkminhN9II/AAAAAAAADk0/wdwbznYJuDk/test.jpg)