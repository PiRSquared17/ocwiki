Gọi dịch vụ với phương thức tuỳ chọn. Giống như [WebService.get](WebServiceClassGet.md) và [WebRequest.post](WebServiceClassPost.md) nhưng WebRequest.request không thiết lập thuộc tính _method_ cho tham số _options_, hàm gọi cần thiết lập thuộc tính này hoặc nó sẽ được mặc định là GET. Nói chung nên sử dụng [WebService.get](WebServiceClassGet.md) hoặc [WebRequest.post](WebServiceClassPost.md) thay cho hàm này.

_path_ là đường dẫn đến dịch vụ, không kể phần đầu (http://domain.name/rest).

Tham số _options_ giống như trong [Ajax.Request](http://api.prototypejs.org/ajax/Ajax/Request/new/), trừ các thuộc tính liên quan đến JSON được thiết lập sẵn và không cần thiết lập lại nữa.

Dữ liệu được truyền dưới dạng đối tượng Javascript thông qua tham số _data_.

Nếu thuộc tính _onFailure_ trong tham số không được thiết lập, hàm xử lý lỗi mặc định (_template.onFailure_) sẽ được gọi.