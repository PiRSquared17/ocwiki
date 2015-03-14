# Vị trí #

Các tệp cấu hình có đuôi .xml được đặt trọng thư mục WebContent/WEB-INF/conf.

Tệp có tên default.xml được nạp đầu tiên, các tệp khác được nạp sau đó theo thứ tự không xác định. Thuộc tính cấu hình trong tệp nạp sau sẽ ghi đè lên thuộc tính trong tệp nạp trước.

# Các thẻ #
  * databaseHost
  * databasePort
  * databaseName
  * domain
  * username
  * password
  * homeDir
  * luceneIndexDirectory
  * articlePath
  * actionPath
  * apiPath
  * restPath
  * templatePath
  * uploadPath
  * userPath
  * mainEntry
  * siteName
  * copyright
  * logoPath
  * lazyStartup
  * tablePrefix
  * defaultTemplate
  * mysqlCommand
  * uploadDir
  * facebookAppId
  * facebookSecret
  * useCDN
  * texCgi
  * recreateDatabaseWhenSetup
  * contactEmail
  * maxAvatarFileSize
  * maxAvatarDimension
  * avatarThumbnailSize
  * maxUploadFileSize
  * action
  * module

## Thẻ action ##
  * name
  * class
  * loginRequired (optional)
  * requiredGroup (optional)
  * container (optional)
  * disabled (optional)

## Thẻ module ##
  * **name**: tên của module
  * **title**: tiêu đề
  * **class** (tuỳ chọn): lớp trợ giúp
  * **position**: mã số vị trí đặt module (tuỳ thuộc vào template)
  * **order**: thứ tự xuất hiện của module (từ nhỏ đến lớn)
  * **page** (tuỳ chọn): tên trang jsp, tương đối với thư mục /templates/xxx/modules, nếu không có thẻ này thì trang mặc định là <tên module>.jsp
  * **inAction**`*` (tuỳ chọn): chỉ bật module với action được chỉ định
  * **articleType**`*` (tuỳ chọn): chỉ bật module khi action liên quan đến kiểu bài viết được chọn
  * **loginRequired** (tuỳ chọn): chỉ bật module nếu người dùng đã đăng nhập
  * **requiredGroup**`*` (tuỳ chọn): chỉ bật module nếu người dùng thuộc một trong những nhóm được chỉ định (có thể có nhiều thẻ này trong một thẻ module)
  * **disabled** (optional): tắt module mà không cần phải xoá nội dung cấu hình

# Lớp Config #

Nội dung của tệp cấu hình được nạp trực tiếp vào đối tượng thuộc lớp Config nhờ thư viện XStream. Trong thời gian sống của application, chỉ có một đối tượng thuộc lớp Config được khởi tạo.