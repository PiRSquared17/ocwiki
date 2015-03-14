## Nội dung ##

Khi người dùng đổi tên tài nguyên, hệ thống tự động phát hiện những [tài nguyên con](ChildResource.md), cháu và đề nghị đổi tên cả những tài nguyên này. Nếu người sử dụng đồng ý, thao tác lưu tài nguyên sẽ dẫn đến sự đổi tên của tất cả các tài nguyên con cháu.

Ví dụ: Khoá học "Nhập môn Hệ quản trị CSDL" có các tài nguyên con "Nhập môn Hệ quản trị CSDL/Lịch học", "Nhập môn Hệ quản trị CSDL/Bài tập lớn". Khi đổi tên khoá học thành "Nhập môn Hệ quản trị CSDL, học kì I năm 2010" thì các tài nguyên con sẽ tự động được đổi tên thành "Nhập môn Hệ quản trị CSDL, học kì I năm 2010/Lịch học" và "Nhập môn Hệ quản trị CSDL, học kì I năm 2010/Bài tập lớn".