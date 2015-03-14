## Hoạt động ##

Hệ thống liên tục theo dõi mọi hành động của người dùng, kể cả thông qua các action, API hay web service.

Nếu trong một khoảng thời gian ngắn người dùng thực hiện nhiều công việc một cách bất thường thì hệ thống sẽ đánh dấu người dùng bị nghi ngờ. Sau đó mọi hành động người dùng thực hiện đều dẫn đến yêu cầu giải một [reCAPTCHA](http://www.google.com/recaptcha).

Nếu người dùng giải được reCAPTCHA thì bộ đếm được reset.FeatureRestoreResource

## Phương pháp xác định lượng hành động ##

Sau khi người dùng đăng nhập, hệ thống luôn lưu giữ thời điểm cuối cùng người dùng có hành động và khoảng thời gian trung bình giữa hai hành động liên tiếp của những hành động gần nhất. Khoảng thời gian này được tính theo công thức [EMA](http://en.wikipedia.org/wiki/Moving_average#Exponential_moving_average) với hệ số alpha lấy từ tệp cấu hình.