Chuyển đổi văn bản từ định dạng nội bộ sang HTML, khi trình bày bất kì bài viết nào cần bọc phần văn bản trong thẻ này. Cú pháp:

```
<ocw:parse resource=... [content=...]>
	[...]
</ocw:parse>
```

Trong đó resource là đối tượng lớp `oop.data.Resource`, trong văn bản có thể có các nội dung động lấy thông tin từ đối tượng này. Thuộc tính content là nội dung cần chuyển đổi, thuộc kiểu String hoặc `oop.data.Text`. Thuộc tính content là tuỳ chọn, thay vào đó có thể đặt văn bản vào thân thẻ.