Gọi dịch vụ theo phương thức POST.

_path_ là đường dẫn đến dịch vụ, không kể phần đầu (http://domain.name/rest).

Tham số _options_ giống như trong [Ajax.Request](http://api.prototypejs.org/ajax/Ajax/Request/new/), trừ các thuộc tính liên quan đến JSON được thiết lập sẵn và không cần thiết lập lại nữa.

Dữ liệu được truyền dưới dạng đối tượng Javascript thông qua tham số _data_.

Nếu thuộc tính _onFailure_ trong tham số không được thiết lập, hàm xử lý lỗi mặc định (_template.onFailure_) sẽ được gọi.

Ví dụ:
```
    WebService.post('/questions/' + resourceId, {
      data: {
          article: question,
          summary: $F('articleEdit-summary'),
          minor: $('articleEdit-minor').checked
      },
      onSuccess: successCallback,
      onFailure: failureCallback
    });
```