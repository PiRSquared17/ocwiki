# Link #

| Sprint03DailyStatus | Daily status of each member |
|:--------------------|:----------------------------|
| Sprint03Retrospective | Tổng kết sprint |
| Sprint03Deliver | Delivered features of this sprint |

# Summary #
  * Bắt đầu: T4, 08/09/2010
  * Kết thúc: T6, 17/09/2010

# Features #

  1. OcwikiF06 : Bài viết liên quan
  1. OcwikiF07 : Tuỳ chỉnh thảo luận
  1. OcwikiF08 : Khoá/Mở khoá bài viết
  1. OcwikiF09 : Bài giải
  1. OcwikiF10 : Sửa câu hỏi
  1. OcwikiF11 : Sửa đề thi

# Sprint Data #
## Resources ##

| Member | Capacity | Experience | Training needed |
|:-------|:---------|:-----------|:----------------|
| Le Ngoc Minh  | 3 | Excellent | 0 |
| Nguyen Viet Ha | 3 | Weak | 1 |
| Nguyen Van Sao  | 3 | Normal | 1 |
| Tran Viet Dung  | 0 | Good | 0 |
| Pham Huy Thang  | 2.5 | Normal | 0 |


## Estimation ##
| **Code** | **Feature** | **LoC** | **Man Hour** | **Phân công** | **Status** | **Ghi chú** |
|:---------|:------------|:--------|:-------------|:----------------|:-----------|:-------------|
| **OcwikiF06** | **Bài viết liên quan** | **500** | **14.0** |  |  |  |
|  -  OcwikiF06T01 | Thiết kế CSDL | ? | 5.0 | Minh | done |  |
|  -  OcwikiF06T02 | Viết DAO (có UT) | 200 | 1.0 | Minh | done | ArticleDAO.fetchRelated |
|  -  OcwikiF06T03 | Hiển thị câu hỏi, đề thi liên quan đến một bài viết | 100 | 5.0 | Thắng |done  |  |
|  -  OcwikiF06T04 | Hiển thị bài viết liên quan đến câu hỏi, đề thi chứa câu hỏi | 100 | 1.5 | Thắng |done  |  |
|  -  OcwikiF06T05 | Hiển thị bài viết liên quan đến đề thi | 100 | 1.5 | Thắng |done  |  |
| **OcwikiF07** | **Tuỳ chỉnh thảo luận** | **190** | **22.0** |  |  |  |
|  -  OcwikiF07T01 | Thiết kế CSDL | ? | 5.0 | Minh | done |  |
|  -  OcwikiF07T02 | Viết DAO (có UT) | 80 | 1.0 | Minh | done |  |
|  -  OcwikiF07T03 | Viết Web service | 40 | 5.0 | Hà |  |  |
|  -  OcwikiF07T04 | Nút ẩn thảo luận | 100 | 4.0 | Hà | done |  |
|  -  OcwikiF07T05 | Nút thích thảo luận | 100 | 5.0 | Hà | done |  |
|  -  OcwikiF07T06 | Thống kê số lần thảo luận bị ẩn/được thích của người dùng | 50 | 2.0 | Hà | done |  |
| **OcwikiF08** | **Khoá/Mở khoá bài viết** | **120** | **12.0** |  |  |  |
|  -  OcwikiF08T01 | Viết Web service | 30 | 2.0 | Thắng | done |  |
|  -  OcwikiF08T02 | Khoá/Mở khoá bài viết | 80 | 5.0 | Thắng |done  |  |
|  -  OcwikiF08T03 | Thiết kế CSDL | ? | 2.0 | Minh | done |  |
|  -  OcwikiF08T04 | Cấm sửa đổi bài viết bị khoá | ? | 2.0 | Minh |  |  |
|  -  OcwikiF08T05 | Viết unit test | 100 | 1.0 | Minh |  |  |
| **OcwikiF09** | **Bài giải** | **240** | **7.0** |  |  |  |
|  -  OcwikiF09T01 | Viết web service | 40 | 3.0 | Sao |  |  |
|  -  OcwikiF09T02 | Tạo bài giải | 100 | 3.0 | Sao |  |  |
|  -  OcwikiF09T03 | Viết unit test | 100 | 1.0 | Minh |  |  |
| **OcwikiF10** | **Sửa câu hỏi** | **240** | **9.5** |  |  |  |
|  -  OcwikiF10T01 | Viết web service (+UT) | 80 | 0.5 | Minh | done |  |
|  -  OcwikiF10T02 | Viết form sửa | 80 | 5.0 | Minh |  done |  |
|  -  OcwikiF10T03 | Viết phần giao tiếp với web service | 50 | 4.0 | Sao |  done |  |
| **OcwikiF11** | **Sửa đề thi** | **210** | **9.5** |  |  |  |
|  -  OcwikiF11T01 | Viết web service (+UT) | 80 | 0.5 | Minh | done |  |
|  -  OcwikiF11T02 | Viết form sửa | 80 | 5.0 | Sao |  |  |
|  -  OcwikiF11T03 | Viết phần giao tiếp với web service | 50 | 4.0 | Sao |  |  |
|  -  OcwikiF11T04 | Tìm câu hỏi DAO (+UT) | 80 | 5.0 | Minh |  | bỏ, sử dụng API cũ |
|  -  OcwikiF11T05 | Tìm câu hỏi web service | 20 | 3.0 | Hà |  | bỏ, sử dụng API cũ |
|  -  OcwikiF11T06 | Tìm câu hỏi dialog | 100 | 6.0 | Sao |  | bỏ, sử dụng API cũ |