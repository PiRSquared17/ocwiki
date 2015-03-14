Tạo liên kết đến hành động, dùng các thẻ này để code rõ ràng và dễ thay đổi hơn.

Cú pháp:
```
<ocw:action(Button|Link) name=... [id=...] [class=...] [confirm=...]>
	[<ocw:param name=... value=...>]
	content
</ocw:action(Button|Link)>
```

Trong đó confirm là một lời gọi hàm JavaScript trẻ về true/false.

Ví dụ:
```
<ocw:actionLink name="article.changelog">
	<ocw:param name="article" value="${action.resource}" />
	Nhật kí
</ocw:actionLink>
```