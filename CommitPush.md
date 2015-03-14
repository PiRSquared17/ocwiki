_Commit_ là thao tác khẳng định các thay đổi đã thực hiện, lưu lại những thay đổi này vào kho _nội bộ_. Sau khi commit, những thay đổi bạn thực hiện vẫn chỉ ở trên máy cá nhân mà chưa thể chia sẻ với các thành viên khác trong nhóm. Để làm điều đó, bạn cần _push_, nghĩa là đấy những phiên bản của bạn lên kho chung trên server.

**Sau mỗi buổi làm việc cần commit và push để tránh mất dữ liệu khi máy tính gặp sự cố.**

## Bỏ qua các tệp cấu hình ##

Trước khi commit lần đầu tiên cần ignore các thư mục / file cấu hình. Cách làm như sau:
  1. Chọn Window > Show view... > Package explorer
  1. Chọn đầu mũi tên trong khung nhìn Package explorer > Filters...
> > ![http://screenshot.tracnghiem-csforce.googlecode.com/hg/view-menu.png](http://screenshot.tracnghiem-csforce.googlecode.com/hg/view-menu.png)
  1. Bỏ chọn ".`*` resource" để hiển thị những thư mục/tệp ẩn
  1. Nhấn phải chuột vào thư mục .setting (thư mục cấu hình project) > Team > Ignore... > Only this folder > OK
  1. Làm tương tự với các file .classpath, .project và .hgignore

Các thao tác này chỉ phải thực hiện một lần cho mỗi project.

## Các bước thực hiện ##

  1. Phải chuột vào project > Team > Commit... Nhập message, nhấn Select all và OK.
  1. Trước khi push, [pull-update](PullUpdate.md) và giải quyết bất cứ conflict nào xảy ra.
  1. Phải chuột vào project > Team > Push.
    * URL: `https://ocwiki.googlecode.com/hg`
    * Username là tên hòm thư gmail (không cần @gmail.com),
    * Password là mật khẩu riêng của Google Code có thể tìm thấy ở địa chỉ https://code.google.com/hosting/settings. Trên trang này bạn cũng có thể cấu hình để Google Code chấp nhận mật khẩu Gmail.