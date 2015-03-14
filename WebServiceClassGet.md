Gọi dịch vụ theo phương thức GET.

_path_ là đường dẫn đến dịch vụ, không kể phần đầu (http://domain.name/rest).

Tham số _options_ giống như trong [Ajax.Request](http://api.prototypejs.org/ajax/Ajax/Request/new/). Các thuộc tính liên quan đến JSON được thiết lập sẵn và không cần thiết lập lại nữa.

Nếu thuộc tính _onFailure_ trong tham số không được thiết lập, hàm xử lý lỗi mặc định (_template.onFailure_) sẽ được gọi.

Ví dụ:
```
WebService.get('/topics/' + topicId, {
    onSuccess : function(transport) {
	alert('success');
    },
    onFailure: function(transport){ 
    	alert('fail');
    }
});
```