_Pull_ là thao tác lấy dữ liệu mới từ kho chung trên server về kho nội bộ trên máy cá nhân. Sau khi pull, working copy chưa thay đổi ngay mà cần thêm một thao tác _update_. Trong trường hợp dữ liệu trong kho nội bộ mới hơn dữ liệu trên server (chỉ xét trong cùng branch) developer cần thực hiện thao tác trộn (_merge_) hai phiên bản với nhau. Một sự xung đột (_conflict_) xảy ra nếu cùng một file bị sửa đổi trong cả hai phiên bản: nội bộ và server. Trong trường hợp này developer phải sửa bằng tay sau đó mở merge.

## Pull ##

  1. Phải chuột vào project > Team > Pull...
    * URL: https://ocwiki.googlecode.com/hg/
    * Có thể có hoặc không có username và password
  1. Nên để lựa chọn "Update after pull" như mặc định.
  1. Tick vào lựa chọn "Merge and, if there are no conflicts, commit after update" cho tiện.
> ![http://images.ocwiki.googlecode.com/hg/pull-normal.png](http://images.ocwiki.googlecode.com/hg/pull-normal.png)

## Merge ##

Khi có sửa đổi mới hơn trên máy cá nhân, bạn có thể nhận được thông báo sau:

![http://images.ocwiki.googlecode.com/hg/conflict-message.png](http://images.ocwiki.googlecode.com/hg/conflict-message.png)

Để giải quyết vấn đề, phải chuột vào project > Team > Merge... > OK

![http://images.ocwiki.googlecode.com/hg/merge-message.png](http://images.ocwiki.googlecode.com/hg/merge-message.png)

Nếu các sửa đổi là xung đột, chi tiết được thể hiện ở view có tên "Mercurial Merge". Có thể bật view này qua Windows > Show view > Other... > Mercurial Merge.

![http://images.ocwiki.googlecode.com/hg/mercurial-merge.png](http://images.ocwiki.googlecode.com/hg/mercurial-merge.png)

Click đúp vào dòng in đậm nào đó mở ra màn hình so sánh hai phiên bản. Bên trái là phiên bản hiện tại trên máy cá nhân, bên phải là phiên bản lấy từ server. Nhiệm vụ của bạn là sửa đổi (nếu cần) phiên bản bên trái sao cho kết hợp được cả hai sự thay đổi của bạn và thành viên khác.

![http://images.ocwiki.googlecode.com/hg/compare.png](http://images.ocwiki.googlecode.com/hg/compare.png)

Sau đó, nhấn phải chuột vào dòng đại diện cho file vừa sửa trong Mercurial Merge và chọn "Mark resolved". Nếu tất cả các conflict đã được mark resolved, hộp hội thoại commit sẽ tự mở ra để bạn có thể commit thay đổi vừa thực hiện.

## Rebase ##

Khi nhiều lập trình viên cùng làm việc trên một branch, thao tác merge phải thực hiện rất thường xuyên dẫn đến nhiều revision không cần thiết và revision graph phức tạp như thế này:

![http://images.ocwiki.googlecode.com/hg/revision-graph.png](http://images.ocwiki.googlecode.com/hg/revision-graph.png)

Để giải quyết vấn đề này cách đơn giản nhất là mỗi lập trình viên làm việc trên một branch riêng biệt và chỉ merge các branch mỗi khi hoàn thành một sprint (tức là 1-2 tuần/lần). Cách làm việc trên nhiều branch có ưu điểm là nếu một thành viên trong nhóm gây lỗi cũng không ảnh hưởng đến cả nhóm, nhược điểm là những thay đổi mới nhất của project không được cả nhóm nhìn thấy ngay lập tức.

Một cách khác có thể giải quyết tình trạng trên là sử dụng tính năng rebase: http://mercurial.selenic.com/wiki/RebaseProject. Đây là một tính năng khá phức tạp, bạn chỉ nên sử dụng khi thực sự hiểu nó làm gì. Để sử dụng tính năng này trong Eclipse, khi pull hãy tick vào lựa chọn "Rebase after pull".