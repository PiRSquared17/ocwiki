# Giới thiệu #
Đây là nơi tập hợp và phân tích các tính năng mới cho Ocwiki. Một tính năng có thể được cài đặt hoặc không, được ưu tiên hoặc để lại sau tuỳ thuộc vào ý kiến đóng góp của các thành viên trong nhóm.

Ở đây chỉ cung cấp mô tả chung chung về tính năng, nếu cần mô tả chi tiết các bạn hãy tạo bài viết riêng cho tính năng của mình.

Sau tên của tính năng là kí hiệu độ ưu tiên:
  * (h): high
  * (m): medium
  * (l): low

# Mục lục #


# Xác thực và phân quyền (Hà) #

## Trang đăng nhập tích hợp ##

Hiện nay chức năng đăng nhập của trang web bị xé nhỏ ra nhiều phần. Yêu cầu cấp thiết là cần có trang đăng nhập tích hợp tất cả các phương thức lại với nhau.

Xem [FeatureIntergratedLogin](FeatureIntergratedLogin.md).

# Soạn thảo (Ngọc) #

## Chèn ảnh từ wiki vào văn bản (h) ##

Cần có nút chèn ảnh từ ocwiki, Flick và một số dịch vụ chia sẻ ảnh khác vào văn bản. Cần có bảng tìm kiếm và có thể xem thông tin trước khi chèn.

Xem [FeatureInsertWikiPicture](FeatureInsertWikiPicture.md).

Độ ưu tiên: **Cao**

## Chèn ảnh từ Flickr vào văn bản (l) ##

Xem [FeatureInsertFlickrPicture](FeatureInsertFlickrPicture.md).

## Tải ảnh tích hợp ##

Trong hộp thoại chèn ảnh nên tích hợp sẵn chức năng tải lên ảnh mới.

Xem [FeatureIntergratedUpload](FeatureIntergratedUpload.md).

## Chèn video vào văn bản (h) ##
Tương tự như ảnh, các dịch vụ cần được hỗ trợ: Youtube, Daily motion, clip.vn,...

Xem [FeatureInsertVideo](FeatureInsertVideo.md).

## Include ##
Include một bài viết vào một văn bản trong tài nguyên khác. Có thể sử dụng cú pháp `<ocw:include article="..." />`

Xem [FeatureInclude](FeatureInclude.md).

## Template ##
Include bài viết nhưng thay thế các tham số bằng nội dung được truyền thêm vào. Chẳng hạn:
```
<ocw:incldue article="...">
  <ocw:param name="..." value="..." />
  <ocw:param name="...">(value)...</ocw:param>
</ocw:include>
```

Xem [FeatureTemplate](FeatureTemplate.md)

# Tài nguyên #

## Tài nguyên nói chung (Hà) ##

### Chức năng vote up - vote down ###
Thay vì chỉ có một nút "Like" như hiện nay, người dùng có thể vote up tài nguyên mà họ cho là hay, hữu ích hoặc vote down tài nguyên họ cho là không hay, không hữu ích.

Xem [FeatureVote](FeatureVote.md).

### So sánh phiên bản ###
So sánh hai phiên bản của một tài nguyên, tô màu các thay đỏi đã thực hiện.

Xem [FeatureDiff](FeatureDiff.md)

### Phục hồi phiên bản ###

### Xoá tài nguyên (h) ###
[FeatureDeleteResource](FeatureDeleteResource.md)

### Phục hồi tài nguyên (h) ###
[FeatureRestoreResource](FeatureRestoreResource.md)

### Đính kèm tập tin ###
Khi sửa tài nguyên cần có chức năng đính kèm tập tin, sửa chữa danh sách tập tin đính kèm. Khi chọn tập tin để đính kèm cần có ô tìm kiếm theo từ khoá và có thể xem trước tập tin một cách thuận tiện.

Xem [FeatureAttach](FeatureAttach.md)

### Tải lên tích hợp với đính kèm ###
Trong hộp hội thoại đính kèm tập tin nên có thêm tab tải lên để người sử dụng có thể tải và đính kèm trong cùng một thao tác.

Xem [FeatureIntegratedFileUpload](FeatureIntegratedFileUpload.md)

### Kết xuất ra định dạng ODT (l) ###
Định dạng tài liệu mở đang dần trở nên phổ biến, được khuyến khích trong các tài liệu pháp quy. Để thúc đẩy xu hướng này chúng ta nên có chức năng kết xuất bài viết thành dạng ODT.

Độ ưu tiên: **thấp**.

Xem [FeatureExportOdt](FeatureExportOdt.md)

### Tạo bài giảng đơn giản ###

Chia sẻ bài giảng nhanh chóng chỉ với 2~3 thao tác. Bài giảng tạo bằng cách này tương tự như tạo theo cách thông thường.

Xem [FeatureSimpleNewArticle](FeatureSimpleNewArticle.md)

## Khoá học (Minh) ##
[Khoá học](Course.md) là tập hợp các tài nguyên, có thể thuộc nhiều loại khác nhau, mô tả đầy đủ nội dung của một khoá học đã diễn ra. [Khoá học](CourseHome.md) cũng được hiểu là một loại tài nguyên, tạo ra "trang chủ" của một khoá học, từ đó dẫn đường đến tất cả các nội dung khác trong khoá học đó.

### Soạn thảo khoá học ###
Xem [FeatureEditCourseHome](FeatureEditCourseHome.md)

### Đổi tên khoá học ###
Do các tài nguyên được gắn với một khoá học dựa vào tên của nó nên khi đổi tên khoá học điều cần thực hiện là đồng thời đổi tên những tài nguyên phụ thuộc vào nó.

Xem [FeatureRenameResource](FeatureRenameResource.md)

## Đề kiểm tra (Minh) ##

### Bảng chọn câu hỏi ###
Khi thêm câu hỏi vào đề cần có một bảng chọn cho phép người sử dụng tìm kiếm bằng từ khoá và xem trước nội dung câu hỏi.

Xem [ResourceChooser](ResourceChooser.md).

## Câu hỏi (Minh) ##

### Nhóm câu hỏi ###
Nhiều khi một số câu hỏi được gom lại thành nhóm có chung một phần mô tả hoặc yêu cầu. Chẳng hạn các câu hỏi có dạng "tìm từ có cách phát âm khác những từ còn lại" hoặc các câu hỏi khác nhau về cùng một đoạn văn.

Xem [QuestionGroup](QuestionGroup.md).

### Câu hỏi nhiều lựa chọn (l) ###

### Câu hỏi ghép cặp (l) ###

[Câu hỏi ghép cặp](MatchingQuestion.md) gồm phần nội dung yêu cầu và các cụm từ chia thành hai cột. Học sinh cần phải ghép ghép các cụm từ của một cột với các cụm từ của cột còn lại.

Cần hỗ trợ việc soạn thảo câu hỏi ghép cặp một cách trực quan và hiệu quả. Xem [FeatureEditMatchingQuestion](FeatureEditMatchingQuestion.md).

### Câu hỏi điền vào chỗ trống (l) ###

[Câu hỏi điền vào chỗ trống](BlankFillingQuestion.md) gồm một câu hoặc một đoạn văn trong đó chứa một hoặc nhiều chỗ trống. Học sinh cần điền vào chỗ trống để hoàn thành câu hoặc đoạn văn đúng nhất.

Xem [FeatureEditBlankFillingQuestion](FeatureEditBlankFillingQuestion.md).

### Câu hỏi trả lời ngắn (l) ###

[Câu hỏi trả lời ngắn](ShortAnswerQuestion.md) gồm nội dung yêu cầu và một ô trống để học sinh điền câu trả lời. "Câu trả lời ngắn" có thể là một cụm từ ngắn hoặc một số nguyên, số thực, ngày tháng.

Xem [FeatureEditShortAnswerQuestion](FeatureEditShortAnswerQuestion.md).

## Chủ đề (Ngọc) ##

### Topic cloud (m) ###
Hiển thị các chủ đề theo sự phổ biến của nó.

### Tự động tạo chủ đề mới (m) ###

Khi người dùng sửa một tài nguyên và xếp cho nó một chủ đề chưa tồn tại, hệ thống tự động tạo ra chủ đề mới.

Xem [FeatureAutocreateTopic](FeatureAutocreateTopic.md).

### Gợi ý chủ đề (l) ###
Sử dụng các thuật toán phân loại văn bản đề gợi ý các chủ đề nên xếp bài viết vào. Khi sửa tài nguyên các chủ đề này sẽ xuất hiện dưới danh sách chủ đề hiện tại và chỉ cần một nhấp chuột để thêm vào danh sách chủ đề.

## Bình luận (Hà) ##

## Tập tin (Sao) ##

### Xác định số lần tập tin được nhúng (m) ###
Xác định được số lần tập tin được nhúng giúp ta tìm ra những tập tin không được sử dụng đến và có kế hoạch dọn dẹp.

Xem [FeatureEmbededCount](FeatureEmbededCount.md).

### Resize file ảnh làm thumbnail (m) ###
Sử dụng ảnh kích thước gốc làm thumbnail khiến trang load chậm và lãng phí dung lượng. Việc tạo sẵn thumbnail bằng cách resize ảnh giải quyết được vấn đề này.

## Tập tin ngoài (Minh) ##
Để giảm tải cho máy chủ, các tập tin có kích thước lớn như video, audio, khoá học dưới dạng nén,... cần được đặt trên các dịch vụ chia sẻ file. Từ đó dẫn đến yêu cầu một [loại tài nguyên mới](UnhostedFile.md) trong đó lưu liên kết đến các tập tin bên ngoài.

### Soạn thảo ###

Xem [FeatureEditUnhostedFile](FeatureEditUnhostedFile.md).

### Báo link hỏng ###

Xem [FeatureReportBrokenLink](FeatureReportBrokenLink.md).

# Tìm kiếm (Sao) #

## Tóm tắt tài nguyên trong kết quả tìm kiếm (h) ##
Ngoài tên/mã tài nguyên, cần hiển thị vài dòng tóm tắt, tốt nhất là tô màu các từ khoá có trong nội dung.

Xem [FeatureSearchSummary](FeatureSearchSummary.md).

# SEO (Quang) #

## Sinh site map tự động (m) ##
Cần có chức năng sinh site map tự động theo định dạng phù hợp với yêu cầu của Google.

Xem [FeatureSiteMap](FeatureSiteMap.md).

## Keyword (l) ##
Khi hiển thị một tài nguyên nào đó thì trong thẻ head có chỉ ra các keyword của tài nguyên đó. Các keyword này lấy từ danh sách chủ đề của tài nguyên.

Xem [FeatureKeyword](FeatureKeyword.md)

# Usability (Ngọc) #

## RSS/Atom (m) ##
Cần có RSS cho mỗi tài nguyên, các chủ đề có RSS chung cho tất cả các tài nguyên trong chủ đề đó, một RSS chung cho toàn bộ trang web.

Xem [FeatureRss](FeatureRss.md).

## Breadcrumb (h) ##
Khi tên của một tài nguyên có dạng A/B/C thì nó được coi là tài nguyên con của B và trang cháu của A. Khi hiển thị A/B/C thì bên dưới tên của nó có liên kết đến A và B. Xem thêm [Wikipedia](http://en.wikipedia.org/wiki/Breadcrumb_%28navigation%29)

Độ ưu tiên: **Cao**

Xem [FeatureBreadcrumb](FeatureBreadcrumb.md)

### Sidebar cấu trúc khoá học (h) ###
Khi truy cập một tài nguyên nào đó thuộc về khoá học nên hiển thị cấu trúc của khoá học ở bên tay trái để người dùng dễ hình dung cấu trúc của khoá học và di chuyển dễ dàng hơn.

Xem [FeatureCourseSidebar](FeatureCourseSidebar.md).

## Gợi ý trang nên xem (m) ##
Dựa vào thói quen của người sử dụng và mức độ phổ biến của bài viết để gợi ý bài viết mà người sử dụng nên đọc.

Xem [FeatureSuggest](FeatureSuggest.md).

# An ninh (Sao) #

## Chống XSS (h) ##
Cần có một giải pháp toàn diện chống tấn công XSS, bao gồm các lớp/hàm/tag/... cần thiết, các quy tắc lập trình, việc sửa chữa lại code đã có.

Độ ưu tiên: **Cao**

## reCAPTCHA (h) ##
Chống tấn công DOS, chống robot.
Xem [FeatureRecaptcha](FeatureRecaptcha.md).

# Cộng đồng (Quang) #

## Thành viên tiêu biểu (h) ##
Mỗi tháng nên giới thiệu một thành viên tiêu biểu trên trang chủ.

## Bài viết được giới thiệu (featured article) (h) ##
Mỗi tuần đặt một bài viết nổi bật trên trang chủ.

## Tính điểm đóng góp (h) ##
Mỗi thành viên sẽ có một số điểm nhất định tuỳ thuộc vào sự đóng góp của họ cho trang web. Điểm này có thể tính bằng số lần sửa đổi một tài nguyên, số lần tài nguyên của họ được vote, số lần bình luận, số lần được vote... Thành viên có điểm cao sẽ được chọn làm thành viên tiêu biểu, được đề cử làm mod.

## Danh sách editor (h) ##
Trong mỗi tài nguyên có thể đưa thêm danh sách những editor (những người đã gửi hoặc sửa chữa tài nguyên đó) để động viên sự đóng góp của cộng đồng.

# Thống kê (Quang) #

## Hiển thị số lần xem một tài nguyên (h) ##
Khi xem tài nguyên trang web hiển thị số lượt khách đã xem. Để tránh gây quá tải, không cập nhật CSDL mỗi lần muốn lưu thông tin mà lưu đệm trong bộ nhớ đến một số lượng nhất định mới chuyển vào CSDL.

## Khảo sát thói quen người dùng (l) ##
Chia người dùng thành nhiều nhóm theo độ tuổi, địa phương, nghề nghiệp và khảo sát các thông tin về thói quen sử dụng trang web (cần yêu cầu cụ thể).

## Thu thập dữ liệu về cách sử dụng (h) ##
Trong quá trình triển khai, tiến hành thu thập dữ liệu về cách sử dụng trang web, lưu trữ trong DB chờ khi có biện pháp xử lý.

## Google Analytics module ##

# Bảo trì (Sao) #

## Xây dựng các công cụ bảo trì như trên Wikipedia (l) ##
Cần liệt kê và mô tả từng công cụ.

Tham khảo: http://en.wikipedia.org/wiki/Special:SpecialPages.

# Khác (Minh) #

## Tài khoản bot (l) ##
Một số tài khoản đặc biệt được đánh dấu là [bot](BotAccount.md) để thực hiện một số công việc một cách tự động. Các tài khoản này có thể thực hiện lượng công việc lớn hơn trong thời gian ngắn mà không bị yêu cầu giải CAPTCHA.

### Đặt cờ bot ###
Xem FeatureSetBotFlag.

### Bỏ cờ bot ###
Xem FeatureUnsetBotFlag.